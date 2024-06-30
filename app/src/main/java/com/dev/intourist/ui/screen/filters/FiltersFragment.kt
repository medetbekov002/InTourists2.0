package com.dev.intourist.ui.screen.filters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.dev.intourist.R
import com.dev.intourist.databinding.FragmentFiltersBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.NumberFormat
import java.util.Currency

class FiltersFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentFiltersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFiltersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.apply {
            title = "Фильтры"
            textAlignment = View.TEXT_ALIGNMENT_CENTER
            setNavigationIcon(R.drawable.ic_cancel) // Установка иконки закрытия
            setNavigationOnClickListener {
                // Обработка нажатия на иконку закрытия
                findNavController().popBackStack()
            }
        }

        /* binding.rangeSlider.setLabelFormatter { value: Float ->
             val format = NumberFormat.getCurrencyInstance()
             format.maximumFractionDigits = 0
             format.currency = Currency.getInstance("KGS")
             format.format(value.toInt())
         }*/
    }
}