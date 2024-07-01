package com.dev.intourist.ui.screen.onboarding

import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dev.intourist.R
import com.dev.intourist.data.local.Pref
import com.dev.intourist.databinding.FragmentOnBoardingBinding
import com.dev.intourist.ui.base.fragment.BaseFragment
import com.google.android.material.button.MaterialButton
import me.relex.circleindicator.CircleIndicator3
import org.koin.androidx.viewmodel.ext.android.viewModel

class OnBoardingFragment :
    BaseFragment<FragmentOnBoardingBinding, OnBoardingViewModel>(R.layout.fragment_on_boarding),
    OnBoardingAdapter.Result {

    override val binding: FragmentOnBoardingBinding by viewBinding()
    override val viewModel: OnBoardingViewModel by viewModel()
    private val pref: Pref by lazy { Pref(requireContext()) }
    private val adapter: OnBoardingAdapter by lazy { OnBoardingAdapter(this) }
    private var count = 0

    override fun initialize() {
        super.initialize()
        binding.apply {
            initAdapter()
        }
    }

    private fun FragmentOnBoardingBinding.initAdapter() {
        try {
            pager.adapter = adapter
            circleIndicator.setViewPager(pager)
        } catch (e: Exception) {
            Log.e("OnBoardingFragment", "Error initializing adapter", e)
        }
    }

    override fun clickNext(btnNext: Button, pos: Int) {
        binding.apply {
            if (pos == 2) {
                btnNext.text = "Перейти"
                pref.saveOnBoarding(true)
                findNavController().navigateUp()
            } else {
                count++
                pager.currentItem = count
            }
        }
    }

    override fun clickBack(btnBack: ImageButton) {
        binding.apply {
            count--
            pager.currentItem = count
        }
    }

    override fun clickSkip() {
        findNavController().navigate(R.id.fragment_auth)
    }
}
