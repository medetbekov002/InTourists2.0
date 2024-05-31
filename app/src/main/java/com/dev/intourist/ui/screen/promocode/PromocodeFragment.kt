package com.dev.intourist.ui.screen.promocode

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
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
        binding.imgCopy.setOnClickListener {
            showToast("Промокод скопирован")
            // Toast.makeText(requireContext(), "Промокод скопирован", Toast.LENGTH_SHORT).show()
        }
        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun showToast(messege: String) {
        val b = ToastLayoutBinding.inflate(layoutInflater)
        val toastView = layoutInflater.inflate(
            R.layout.toast_layout,
            b.toastLayoutContainer
        )
        b.imageIcon.setImageResource(R.drawable.ic_check_white)
        b.closeToast.setImageResource(R.drawable.ic_cancel_white)
        b.toastMessege.text = messege
        with(Toast(requireContext())) {
            duration = Toast.LENGTH_SHORT
            view = toastView
            show()
        }
        b.closeToast.setOnClickListener {
        toastView.visibility = View.GONE
        }
    }
}