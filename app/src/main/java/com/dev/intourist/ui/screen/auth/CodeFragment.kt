package com.dev.intourist.ui.screen.auth

import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.navigation.fragment.findNavController
import com.dev.intourist.R
import com.dev.intourist.databinding.FragmentCodeBinding

class CodeFragment : Fragment() {
    private lateinit var binding: FragmentCodeBinding
    private lateinit var countDownTimer: CountDownTimer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickers()
        setupTimer()
        binding.run {
            val textWatchers = listOf<TextWatcher>(
                createTextWatcher(etCode1, etCode2),
                createTextWatcher(etCode2, etCode3),
                createTextWatcher(etCode3, etCode4),
                createTextWatcher(etCode4, null)
            )
            for ((index, et) in listOf(etCode1, etCode2, etCode3, etCode4).withIndex()) {
                et.addTextChangedListener(textWatchers[index])
                et.setOnKeyListener { _, keyCode, event ->
                    if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN) {
                        if (et.text.isNullOrEmpty()) {
                            // Если EditText пустой, удаляем текст из предыдущего EditText
                            val prevIndex = index - 1
                            if (prevIndex >= 0) {
                                val prevEditText = listOf(
                                    etCode1,
                                    etCode2,
                                    etCode3,
                                    etCode4
                                )[prevIndex]
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
        val code1Length = binding.etCode1.text?.length ?: 0
        val code2Length = binding.etCode2.text?.length ?: 0
        val code3Length = binding.etCode3.text?.length ?: 0
        val code4Length = binding.etCode4.text?.length ?: 0

        if (code1Length == 1 && code2Length == 1 && code3Length == 1 && code4Length == 1) {
            binding.btnContinue.backgroundTintList =
                getColorStateList(requireContext(),R.color.dark_blue_for_button)
            binding.btnContinue.setTextColor(getResources().getColor(R.color.white))
        } else {
            binding.btnContinue.backgroundTintList = getColorStateList(requireContext(),R.color.white_for_btn)
            binding.btnContinue.setTextColor(getResources().getColor(R.color.grey))
        }
    }
    private fun initClickers() {
        binding.run {
            btnContinue.setOnClickListener {
                findNavController().navigate(R.id.fragment_home)
            }
        }


    }

    private fun setupTimer() {
        countDownTimer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsUntilFinished = millisUntilFinished / 1000
                binding.tvSendCodeInTime.text = "${secondsUntilFinished / 60}:${String.format("%02d", secondsUntilFinished % 60)}"
            }

            override fun onFinish() {
                binding.tvSendCodeIn.text = "Отправить повторно"
                binding.tvSendCodeInTime.visibility = View.INVISIBLE
                binding.tvSendCodeIn.setOnClickListener {
                    binding.tvSendCodeIn.text = "Отправить повторно через"
                    setupTimer()
                    binding.tvSendCodeInTime.visibility = View.VISIBLE
                    startTimer()
                }
            }
        }
        startTimer()
    }

    private fun startTimer() {
        countDownTimer.start()
    }
}