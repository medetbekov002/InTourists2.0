package com.dev.intourist.presentation.ui.screen.faqimport android.os.Bundleimport android.view.Viewimport androidx.navigation.fragment.findNavControllerimport com.dev.intourist.Rimport com.dev.intourist.databinding.FragmentFaqBindingimport com.dev.intourist.presentation.base.fragment.BaseFragmentimport org.koin.androidx.viewmodel.ext.android.viewModelimport by.kirich1409.viewbindingdelegate.viewBindingimport com.google.android.gms.maps.GoogleMapclass FAQFragment :    BaseFragment<FragmentFaqBinding, FAQViewModel>(R.layout.fragment_faq) {    private var isAnswerVisible1 = false    private var isAnswerVisible2 = false    private var isAnswerVisible3 = false    private var isAnswerVisible4 = false    private var isAnswerVisible5 = false    override val binding: FragmentFaqBinding by viewBinding()    override val viewModel: FAQViewModel by viewModel()    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {        super.onViewCreated(view, savedInstanceState)        binding.apply {            faqTitle.setOnClickListener {                findNavController().popBackStack()            }            btnFaq1.setOnClickListener {                toggleAnswer1Visibility()                toggle1ArrowIcon()            }            btnFaq2.setOnClickListener {                toggleAnswer2Visibility()                toggle2ArrowIcon()            }            btnFaq3.setOnClickListener {                toggleAnswer3Visibility()                toggle3ArrowIcon()            }            btnFaq4.setOnClickListener {                toggleAnswer4Visibility()                toggle4ArrowIcon()            }            btnFaq5.setOnClickListener {                toggleAnswer5Visibility()                toggle5ArrowIcon()            }        }    }    override fun onMapReady(googleMap: GoogleMap) {    }    private fun toggle1ArrowIcon() {        val arrowDrawable = if (isAnswerVisible1) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down        binding.btnFaq1.setCompoundDrawablesWithIntrinsicBounds(0, 0, arrowDrawable, 0)    }    private fun toggle2ArrowIcon() {        val arrowDrawable = if (isAnswerVisible2) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down        binding.btnFaq2.setCompoundDrawablesWithIntrinsicBounds(0, 0, arrowDrawable, 0)    }    private fun toggle3ArrowIcon() {        val arrowDrawable = if (isAnswerVisible3) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down        binding.btnFaq3.setCompoundDrawablesWithIntrinsicBounds(0, 0, arrowDrawable, 0)    }    private fun toggle4ArrowIcon() {        val arrowDrawable = if (isAnswerVisible4) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down        binding.btnFaq4.setCompoundDrawablesWithIntrinsicBounds(0, 0, arrowDrawable, 0)    }    private fun toggle5ArrowIcon() {        val arrowDrawable = if (isAnswerVisible5) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down        binding.btnFaq5.setCompoundDrawablesWithIntrinsicBounds(0, 0, arrowDrawable, 0)    }    private fun toggleAnswer1Visibility() {        isAnswerVisible1 = !isAnswerVisible1        binding.tvFaqAnswers1.visibility = if (binding.tvFaqAnswers1.visibility == View.VISIBLE) {            View.GONE        } else {            View.VISIBLE        }    }    private fun toggleAnswer2Visibility() {        isAnswerVisible2 = !isAnswerVisible2        binding.tvFaqAnswers2.visibility = if (binding.tvFaqAnswers2.visibility == View.VISIBLE) {            View.GONE        } else {            View.VISIBLE        }    }    private fun toggleAnswer3Visibility() {        isAnswerVisible3 = !isAnswerVisible3        binding.tvFaqAnswers3.visibility = if (binding.tvFaqAnswers3.visibility == View.VISIBLE) {            View.GONE        } else {            View.VISIBLE        }    }    private fun toggleAnswer4Visibility() {        isAnswerVisible4 = !isAnswerVisible4        binding.tvFaqAnswers4.visibility = if (binding.tvFaqAnswers4.visibility == View.VISIBLE) {            View.GONE        } else {            View.VISIBLE        }    }    private fun toggleAnswer5Visibility() {        isAnswerVisible5 = !isAnswerVisible5        binding.tvFaqAnswers5.visibility = if (binding.tvFaqAnswers5.visibility == View.VISIBLE) {            View.GONE        } else {            View.VISIBLE        }    }}