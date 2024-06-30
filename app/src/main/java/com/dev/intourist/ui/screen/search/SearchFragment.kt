package com.dev.intourist.ui.screen.search

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import com.dev.intourist.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.svSearchTours.setIconifiedByDefault(false)
        binding.svSearchTours.isIconified = false
        binding.svSearchTours.requestFocus()
        binding.svSearchTours.postDelayed({
            val imm =
                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(
                binding.svSearchTours.findViewById(androidx.appcompat.R.id.search_src_text),
                InputMethodManager.SHOW_IMPLICIT
            )
        }, 200)
        binding.btnCancel.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}