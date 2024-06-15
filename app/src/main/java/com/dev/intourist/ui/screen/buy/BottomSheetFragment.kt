package com.dev.intourist.ui.screen.buy

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.dev.intourist.R
import com.dev.intourist.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class BottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomSheetBinding
    private val calendar = Calendar.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            val textWatcher = object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    updateButtonColor()
                }
            }

            etDate.setOnClickListener {
                showDatePickerAlertDialog()
            }
            etDate.addTextChangedListener(textWatcher)
            etTour.addTextChangedListener(textWatcher)
            etFio.addTextChangedListener(textWatcher)
            etAmount.addTextChangedListener(textWatcher)
            etPhoneNumber.addTextChangedListener(textWatcher)

            btnCancel.setOnClickListener {
                dismiss()
            }
            btnSend.setOnClickListener {
                dismiss()
            }
        }
    }

    private fun showDatePickerAlertDialog() {
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDay = Calendar.getInstance()
                selectedDay.set(year, monthOfYear, dayOfMonth)
                val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDay.time)

                binding.etDate.setText(formattedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun FragmentBottomSheetBinding.updateButtonColor() {
        val isAllFieldsFilled = etDate.text.isNotEmpty() &&
                etTour.text.isNotEmpty() &&
                etFio.text.isNotEmpty() &&
                etAmount.text.isNotEmpty() &&
                etPhoneNumber.text.length == 13

        val colorResId = if (isAllFieldsFilled) R.color.dark_blue_for_button else R.color.light_grey
        val colorTextId = if (isAllFieldsFilled) R.color.white else R.color.gray
        btnSend.setTextColor(resources.getColor(colorTextId))
        btnSend.backgroundTintList = ContextCompat.getColorStateList(requireContext(), colorResId)
    }
}

