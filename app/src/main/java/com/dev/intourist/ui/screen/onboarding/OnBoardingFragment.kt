package com.dev.intourist.ui.screen.onboarding

import android.util.Log
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

    override val binding: FragmentOnBoardingBinding by viewBinding(FragmentOnBoardingBinding::bind)
    override val viewModel: OnBoardingViewModel by viewModel()
    private val pref: Pref by lazy { Pref(requireContext()) }
    private val adapter: OnBoardingAdapter by lazy { OnBoardingAdapter(this) }
    var count = 0

    override fun initialize() {
        super.initialize()
        initAdapter()
    }

    private fun initAdapter() {
        try {
            binding.pager.adapter = adapter
            val indicator = view?.findViewById<CircleIndicator3>(R.id.circle_indicator)
            indicator?.setViewPager(binding.pager)
        } catch (e: Exception) {
            Log.e("ololo", "OBF.iA.catch:$e")
        }

    }

    override fun clickNext(btnNext: MaterialButton, pos: Int) {
        if (pos == 2) {
            btnNext.text = "Перейти"
            pref.saveOnBoarding(true)
            findNavController().navigateUp()
        } else {
            count++
            binding.pager.currentItem = count
        }
    }

    override fun clickBack(btnBack: MaterialButton) {
        count--
        binding.pager.currentItem = count
    }

    override fun clickScip() {
        findNavController().navigateUp()
    }
}