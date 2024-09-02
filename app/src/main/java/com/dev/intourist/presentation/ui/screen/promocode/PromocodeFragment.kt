package com.dev.intourist.presentation.ui.screen.promocode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dev.intourist.R
import com.dev.intourist.databinding.FragmentPromocodeBinding
import com.dev.intourist.databinding.ToastLayoutBinding
import com.dev.intourist.presentation.base.fragment.BaseFragment
import com.google.android.gms.maps.GoogleMap


class PromocodeFragment :
    BaseFragment<FragmentPromocodeBinding, PromocodeViewModel>(R.layout.fragment_promocode) {
    override val binding: FragmentPromocodeBinding by viewBinding(FragmentPromocodeBinding::bind)
    override val viewModel: PromocodeViewModel by viewModel()

//    private lateinit var binding: FragmentPromocodeBinding
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentPromocodeBinding.inflate(inflater, container, false)
//        return binding.root
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            containerPromocode.setOnClickListener {
                showToast("Промокод скопирован")
            }
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
    }

    private fun showToast(message: String) {
        val inflater = LayoutInflater.from(requireContext())
        val binding = ToastLayoutBinding.inflate(inflater)
        val toastView = binding.root

        val icCheck = ContextCompat.getDrawable(requireContext(), R.drawable.ic_check)
        icCheck?.setTint(ContextCompat.getColor(requireContext(), R.color.white))
        val icCancel = ContextCompat.getDrawable(requireContext(), R.drawable.ic_cancel)
        icCancel?.setTint(ContextCompat.getColor(requireContext(), R.color.white))

        binding.apply {
            imageIcon.setImageDrawable(icCheck)
            closeToast.setImageDrawable(icCancel)
            toastMessege.text = message
        }

        with(Toast(requireContext())) {
            duration = Toast.LENGTH_SHORT
            view = toastView
            show()
        }
    }
}