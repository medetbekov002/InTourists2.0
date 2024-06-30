package com.dev.intourist.ui.screen.payment.payment

//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.TextView
//import androidx.core.view.ViewCompat
//import androidx.core.view.isVisible
//import androidx.fragment.app.Fragment
//import com.dev.intourist.R
//import com.dev.intourist.ui.screen.payment.model.PaymentError
//import com.google.android.gms.wallet.Wallet
//import com.google.android.gms.wallet.WalletConstants
//import com.google.android.gms.wallet.button.ButtonOptions
//import com.google.android.gms.wallet.button.PayButton
//import money.paybox.payboxsdk.PayboxSdk
//import money.paybox.payboxsdk.config.Language
//import money.paybox.payboxsdk.config.PaymentSystem
//import money.paybox.payboxsdk.config.Region
//import money.paybox.payboxsdk.config.RequestMethod
//import money.paybox.payboxsdk.interfaces.WebListener
////import money.paybox.payboxsdk.models.PaymentError
//import money.paybox.payboxsdk.view.PaymentView
//import org.json.JSONArray
//import org.json.JSONObject
//
//class PaymentFragment : Fragment(), WebListener {
//    lateinit var loaderView: View
//    lateinit var outputTextView: TextView
//    lateinit var paymentView: PaymentView
//
//    //Необходимо заменить тестовый secretKey и merchantId на свой
//    private val secretKey = "UnPLLvWsuXPyC3wd"
//    private val merchantId = 503623
//    private val googleMerchantId = "BCR2DN6T57R772SF"
//    private val gateway = "payboxmoney"
//    private val gatewayMerchantId = "paybox_pp"
//    private val kzCountryCode = "KZ"
//    private val ruCountryCode = "RU"
//    private val currency = "KZT"
//
//    //Если email или phone не указан, то выбор будет предложен на сайте платежного гейта
//    private val email = "user@mail.com"
//    private val phone = "77012345678"
//
//    private var paymentId: String? = null
//    val sdk by lazy { PayboxSdk.initialize(merchantId, secretKey) }
//
//    private val allowedCardNetworks = JSONArray(
//        listOf(
//            "O! Деньги",
//            "MBANK",
//            "Optima Bank",
//            "RSK Bank"
//        )
//    )
//
//    private val allowedCardAuthMethods = JSONArray(
//        listOf(
//            "PAN_ONLY",
//            "CRYPTOGRAM_3DS"
//        )
//    )
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.fragment_payment, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        loaderView = view.findViewById(R.id.loaderView)
//        outputTextView = view.findViewById(R.id.outputTextView)
//        paymentView = view.findViewById(R.id.paymentView)
//        ViewCompat.setTranslationZ(paymentView, 10f)
//
//        val googlePayButton: PayButton = view.findViewById(R.id.buttonPaymentByGoogle)
//
//        val googlePaymentsClient = Wallet.getPaymentsClient(
//            requireContext(),
//            Wallet.WalletOptions.Builder()
//                .setEnvironment(WalletConstants.ENVIRONMENT_TEST)
//                .setTheme(WalletConstants.THEME_LIGHT)
//                .build()
//        )
//
//        googlePayButton.initialize(
//            ButtonOptions.newBuilder()
////                .setButtonTheme(ButtonOptions.ButtonTheme.LIGHT)
////                .setButtonType(ButtonOptions.ButtonType.BUY)
//                .setCornerRadius(100)
//                .setAllowedPaymentMethods(JSONArray().put(cardPaymentMethod()).toString())
//                .build()
//        )
//
//        sdk.setPaymentView(paymentView)
//        paymentView.listener = this
//        sdk.config().testMode(false)  //По умолчанию тестовый режим включен
//        //Выбор региона
//        sdk.config().setRegion(Region.DEFAULT) //По умолчанию установлен Region.DEFAULT
//        //Выбор платежной системы:
//        sdk.config().setPaymentSystem(PaymentSystem.NONE)
//        //Выбор валюты платежа:
//        sdk.config().setCurrencyCode("KZT")
//        //Активация автоклиринга:
//        sdk.config().autoClearing(true)
//        //Установка кодировки:
//        sdk.config().setEncoding("UTF-8") //по умолчанию UTF-8
//        //Время жизни рекурентного профиля:
//        sdk.config().setRecurringLifetime(1) //по умолчанию 36 месяцев
//        //Время жизни платежной страницы, в течение которого платеж должен быть завершен:
//        sdk.config().setPaymentLifetime(300)  //по умолчанию 300 секунд
//        //Включение режима рекурентного платежа:
//        sdk.config().recurringMode(false)  //по умолчанию отключен
//        //Номер телефона клиента, будет отображаться на платежной странице. Если не указать, то будет предложено ввести на платежной странице:
//        sdk.config().setUserPhone(phone)
//        //Email клиента, будет отображаться на платежной странице. Если не указать email, то будет предложено ввести на платежной странице:
//        sdk.config().setUserEmail(email)
//        //Язык платежной страницы:
//        sdk.config().setLanguage(Language.ru)
//        //Для передачи информации от платежного гейта:
//        sdk.config().setCheckUrl("http://test.paybox.kz/")
//        sdk.config().setResultUrl("http://test.paybox.kz/")
//        sdk.config().setRefundUrl("http://test.paybox.kz/")
//        sdk.config().setClearingUrl("http://test.paybox.kz/")
//        sdk.config().setRequestMethod(RequestMethod.GET)
//        //Для выбора Frame вместо платежной страницы
//        sdk.config().setFrameRequired(true) //false по умолчанию
//
//        //Инициализация нового платежа
//        view.findViewById<Button>(R.id.buttonInitPayment).setOnClickListener {
//            val amount = 10f
//            val description = "some description"
//            val orderId = "1234"
//            val userId = "1234"
//            val extraParams: HashMap<String, String>? = null
//
//            outputTextView.text = ""
//            paymentView.visibility = View.VISIBLE
//
//            sdk.createPayment(amount, description, orderId, userId, extraParams) { payment, error ->
//                if (error != null) {
//                    Log.e("initPAY", error.description)
//                }
//                paymentView.visibility = View.GONE
//            }
//        }
//
//        view.findViewById<Button>(R.id.buttonDirectPayment).setOnClickListener {
//            val amount = 10f
//            val userId = "1234"
//            val cardToken = "your_card_token"
//            val description = "some description"
//            val orderId = "1234"
//            val extraParams: HashMap<String, String>? = null
//
//            outputTextView.text = ""
//            paymentView.visibility = View.VISIBLE
//
//            sdk.createCardPayment(
//                amount,
//                userId,
//                cardToken,
//                description,
//                orderId,
//                extraParams
//            ) { payment, error ->
//                if (error != null) {
//                    Log.e("initDirectPAY", error.description)
//                }
//
//                payment?.paymentId?.let { paymentId ->
//                    sdk.createNonAcceptancePayment(paymentId) { payment2, error2 ->
//                        if (error2 != null) {
//                            Log.e("makeDirectPAY", error2.description)
//                        }
//                        Log.e("initPAY", payment2?.status ?: "")
//                    }
//                }
//
//                paymentView.visibility = View.GONE
//            }
//        }
//
//        //Отображение списка привязанных карт
//        view.findViewById<Button>(R.id.buttonShowCards).setOnClickListener {
//            val userId = "1234"
//
//            outputTextView.text = ""
//            loaderView.visibility = View.VISIBLE
//
//            sdk.getAddedCards(userId) { cards, error ->
//                loaderView.visibility = View.GONE
//
//                if (error != null) {
//                    showError(error.description)
//                    return@getAddedCards
//                }
//
//                if (cards != null) {
//                    if (cards.isEmpty()) {
//                        showError("Список пуст")
//                        return@getAddedCards
//                    }
//
//                    val message = StringBuilder()
//
//                    for (card in cards) {
////                        message.append("Card ID: ${card.cardId}, PAN: ${card.pan}\n")
//                    }
//
//                    showInfo(message.toString())
//                }
//            }
//        }
//    }
//
//    override fun onLoadStarted() {
//        // Показать индикатор загрузки
//        loaderView.visibility = View.VISIBLE
//    }
//
//    override fun onLoadFinished() {
//        // Скрыть индикатор загрузки
//        loaderView.visibility = View.GONE
//    }
//
////    override fun onPageStarted(url: String, navigationType: Int) {
////        // Логирование начала загрузки страницы
////        Log.d("PaymentFragment", "Page started loading: $url with navigation type: $navigationType")
////    }
////
////    override fun onPageFinished(url: String) {
////        // Логирование окончания загрузки страницы
////        Log.d("PaymentFragment", "Page finished loading: $url")
////    }
////
////    override fun onError(error: PaymentError) {
////        // Обработка ошибки и отображение сообщения об ошибке
////        Log.e("PaymentFragment", "Error: ${error.description}")
////        showError("Ошибка: ${error.description}")
////    }
//
//    private fun showError(message: String) {
//        outputTextView.text = message
//        outputTextView.setTextColor(resources.getColor(R.color.red, null))
//        outputTextView.isVisible = true
//    }
//
//    private fun showInfo(message: String) {
//        outputTextView.text = message
//        outputTextView.setTextColor(resources.getColor(R.color.green, null))
//        outputTextView.isVisible = true
//    }
//
//    private fun cardPaymentMethod(): JSONObject {
//        val parameters = JSONObject()
//            .put("allowedAuthMethods", allowedCardAuthMethods)
//            .put("allowedCardNetworks", allowedCardNetworks)
//            .put("billingAddressRequired", true)
//            .put(
//                "billingAddressParameters", JSONObject()
//                    .put("format", "FULL")
//                    .put("phoneNumberRequired", true)
//            )
//
//        return JSONObject()
//            .put("type", "CARD")
//            .put("parameters", parameters)
//            .put("tokenizationSpecification", gatewayTokenizationSpecification())
//    }
//
//    private fun gatewayTokenizationSpecification(): JSONObject {
//        return JSONObject()
//            .put("type", "PAYMENT_GATEWAY")
//            .put(
//                "parameters", JSONObject()
//                    .put("gateway", gateway)
//                    .put("gatewayMerchantId", gatewayMerchantId)
//            )
//    }
//}
