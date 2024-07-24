package com.dev.intourist.ui.screen.search

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dev.intourist.R
import com.dev.intourist.databinding.FragmentSearchBinding
import com.dev.intourist.presentation.base.fragment.BaseFragment
import com.google.android.gms.maps.GoogleMap


class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>(R.layout.fragment_search) {

    override val binding: FragmentSearchBinding by viewBinding(FragmentSearchBinding::bind)
    override val viewModel: SearchViewModel by viewModel()

//    private lateinit var binding: FragmentSearchBinding
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = FragmentSearchBinding.inflate(inflater, container, false)
//        return binding.root
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            svSearchTours.setIconifiedByDefault(false)
            svSearchTours.isIconified = false
            svSearchTours.requestFocus()
            svSearchTours.postDelayed({
                val imm =
                    requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(
                    svSearchTours.findViewById(androidx.appcompat.R.id.search_src_text),
                    InputMethodManager.SHOW_IMPLICIT
                )
            }, 200)
            btnCancel.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
    }
}