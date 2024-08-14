package com.dev.intourist.ui.screen.otp

import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dev.intourist.R
import com.dev.intourist.databinding.FragmentCodeBinding
import com.dev.intourist.presentation.base.fragment.BaseFragment
import com.google.android.gms.maps.GoogleMap

class CodeFragment : BaseFragment<FragmentCodeBinding, CodeViewModel>(R.layout.fragment_code) {
    override val binding: FragmentCodeBinding by viewBinding(FragmentCodeBinding::bind)
    override val viewModel: CodeViewModel by viewModel()
    private lateinit var countDownTimer: CountDownTimer


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupTimer()
        initClickers()
    }

    override fun onMapReady(googleMap: GoogleMap) {
    }

    private fun setupViews() {
        binding.apply {
            val textWatchers = listOf<TextWatcher>(
                createTextWatcher(etCode1, etCode2),
                createTextWatcher(etCode2, etCode3),
                createTextWatcher(etCode3, etCode4),
                createTextWatcher(etCode4, null)
            )
            listOf(etCode1, etCode2, etCode3, etCode4).forEachIndexed { index, et ->
                et.addTextChangedListener(textWatchers[index])
                et.setOnKeyListener { _, keyCode, event ->
                    if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN) {
                        if (et.text.isNullOrEmpty()) {
                            val prevIndex = index - 1
                            if (prevIndex >= 0) {
                                val prevEditText =
                                    listOf(etCode1, etCode2, etCode3, etCode4)[prevIndex]
                                prevEditText.setText("")
                                prevEditText.requestFocus()
                            }
                        }
                    }
                    false
                }
            }
        }
    }

    private fun createTextWatcher(currentEditText: EditText, nextEditText: EditText?): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val text = s?.toString()
                if (!text.isNullOrEmpty()) {
                    currentEditText.setBackgroundResource(R.drawable.bg_for_et_code_right)
                } else {
                    currentEditText.setBackgroundResource(R.drawable.bg_for_auth_views)
                }
                nextEditText?.requestFocus()
                checkCodeLength()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
    }

    private fun checkCodeLength() {
        binding.apply {
            val code1Length = etCode1.text?.length ?: 0
            val code2Length = etCode2.text?.length ?: 0
            val code3Length = etCode3.text?.length ?: 0
            val code4Length = etCode4.text?.length ?: 0

            if (code1Length == 1 && code2Length == 1 && code3Length == 1 && code4Length == 1) {
                btnContinue.backgroundTintList =
                    getColorStateList(requireContext(), R.color.dark_blue_for_button)
                btnContinue.setTextColor(resources.getColor(R.color.white))
            } else {
                btnContinue.backgroundTintList =
                    getColorStateList(requireContext(), R.color.white_for_btn)
                btnContinue.setTextColor(resources.getColor(R.color.grey))
            }
        }
    }

    private fun initClickers() {
        binding.btnContinue.setOnClickListener {
            findNavController().navigate(R.id.fragment_home)
        }
    }

    private fun setupTimer() {
        countDownTimer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsUntilFinished = millisUntilFinished / 1000
                binding.tvSendCodeInTime.text = "${secondsUntilFinished / 60}:${
                    String.format(
                        "%02d",
                        secondsUntilFinished % 60
                    )
                }"
            }

            override fun onFinish() {
                binding.apply {
                    tvSendCodeIn.text = "Отправить повторно"
                    tvSendCodeInTime.visibility = View.INVISIBLE
                    tvSendCodeIn.setOnClickListener {
                        tvSendCodeIn.text = "Отправить повторно через"
                        setupTimer()
                        tvSendCodeInTime.visibility = View.VISIBLE
                        startTimer()
                    }
                }
            }
        }
        startTimer()
    }

    private fun startTimer() {
        countDownTimer.start()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        // Остановка таймера и освобождение binding
        countDownTimer.cancel()
    }
}
