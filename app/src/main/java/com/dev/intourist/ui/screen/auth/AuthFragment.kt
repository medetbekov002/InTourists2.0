package com.dev.intourist.ui.screen.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.dev.intourist.R
import com.dev.intourist.databinding.FragmentAuthBinding

class AuthFragment : Fragment() {
    private lateinit var binding: FragmentAuthBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etPhoneNumber.addTextChangedListener( object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val colorResId = if (s?.length == 13) R.color.dark_blue_for_button else R.color.light_grey
                binding.btnContinue.backgroundTintList = ContextCompat.getColorStateList(requireContext(), colorResId) }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.btnContinue.setOnClickListener {
//            findNavController().navigate(R.id.action_authFragment_to_codeFragment)
        }

        binding.btnSkip.setOnClickListener {
//            findNavController().navigate(R.id.action_authFragment_to_homeFragment)
        }

        binding.btnGoogle.setOnClickListener {
            // inter with google
        }
    }
}