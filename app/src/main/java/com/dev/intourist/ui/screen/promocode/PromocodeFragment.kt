package com.dev.intourist.ui.screen.promocode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dev.intourist.R
import com.dev.intourist.databinding.FragmentPromocodeBinding
import com.dev.intourist.databinding.ToastLayoutBinding


class PromocodeFragment : Fragment() {
    private lateinit var binding: FragmentPromocodeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPromocodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerPromocode.setOnClickListener {
            showToast("Промокод скопирован")
            // Toast.makeText(requireContext(), "Промокод скопирован", Toast.LENGTH_SHORT).show()
        }
        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }
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