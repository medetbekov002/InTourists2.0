package com.dev.intourist.ui.screen.favorite

import by.kirich1409.viewbindingdelegate.viewBinding
import com.dev.intourist.R
import com.dev.intourist.databinding.FragmentFavoriteBinding
import com.dev.intourist.ui.base.fragment.BaseFragment
import com.dev.intourist.ui.screen.tours.ToursViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment :
    BaseFragment<FragmentFavoriteBinding, ToursViewModel>(R.layout.fragment_favorite) {

    override val binding: FragmentFavoriteBinding by viewBinding(FragmentFavoriteBinding::bind)
    override val viewModel: ToursViewModel by viewModel()

}