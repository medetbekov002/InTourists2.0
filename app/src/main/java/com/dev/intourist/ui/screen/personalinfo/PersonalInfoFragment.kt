package com.dev.intourist.ui.screen.personalinfo

import by.kirich1409.viewbindingdelegate.viewBinding
import com.dev.intourist.R
import com.dev.intourist.databinding.FragmentPersonalInfoBinding
import com.dev.intourist.ui.base.fragment.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class PersonalInfoFragment :
    BaseFragment<FragmentPersonalInfoBinding, PersonalInfoViewModel>(R.layout.fragment_personal_info) {

    override val binding: FragmentPersonalInfoBinding by viewBinding(FragmentPersonalInfoBinding::bind)
    override val viewModel: PersonalInfoViewModel by viewModel()

}