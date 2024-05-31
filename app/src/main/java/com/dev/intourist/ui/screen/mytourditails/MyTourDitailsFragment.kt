package com.dev.intourist.ui.screen.mytourditails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.intourist.R
import com.dev.intourist.databinding.FragmentFavoriteBinding
import com.dev.intourist.databinding.FragmentMyTourDitailsBinding


class MyTourDitailsFragment : Fragment() {

    private lateinit var binding: FragmentMyTourDitailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyTourDitailsBinding.inflate(inflater,container,false)
        return binding.root
    }

}