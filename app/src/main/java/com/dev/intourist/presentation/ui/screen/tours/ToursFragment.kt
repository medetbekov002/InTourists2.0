package com.dev.intourist.presentation.ui.screen.tours

import by.kirich1409.viewbindingdelegate.viewBinding
import com.dev.intourist.R
import com.dev.intourist.databinding.FragmentFavoriteBinding
import com.dev.intourist.presentation.base.fragment.BaseFragment
import com.google.android.gms.maps.GoogleMap
import org.koin.androidx.viewmodel.ext.android.viewModel

class ToursFragment :
    BaseFragment<FragmentFavoriteBinding, ToursViewModel>(R.layout.fragment_favorite) {

    override val binding: FragmentFavoriteBinding by viewBinding(FragmentFavoriteBinding::bind)
    override val viewModel: ToursViewModel by viewModel()
    override fun onMapReady(googleMap: GoogleMap) {

    }

}