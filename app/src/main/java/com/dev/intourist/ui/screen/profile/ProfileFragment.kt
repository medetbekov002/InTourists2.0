package com.dev.intourist.ui.screen.profile

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dev.intourist.R
import com.dev.intourist.databinding.FragmentProfileBinding
import com.dev.intourist.presentation.base.fragment.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment :
    BaseFragment<FragmentProfileBinding, ProfileViewModel>(R.layout.fragment_profile) {

    override val binding: FragmentProfileBinding by viewBinding(FragmentProfileBinding::bind)
    override val viewModel: ProfileViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnAuthRegistration.setOnClickListener {
                findNavController().navigate(R.id.fragment_auth)
            }
            btnPersonalInfo.setOnClickListener {
                findNavController().navigate(R.id.fragment_personal_info)
            }
            btnNotifications.setOnClickListener {
                findNavController().navigate(R.id.fragment_settings)
            }
            btnPrivacyAndPolicy.setOnClickListener {
                findNavController().navigate(R.id.fragment_privacy_policy)
            }
            btnTermsOfUse.setOnClickListener {
                findNavController().navigate(R.id.fragment_terms_of_use)
            }
            btnQuestionAndAnswer.setOnClickListener {
                findNavController().navigate(R.id.fragment_faq)
            }
            btnAboutUs.setOnClickListener {
                findNavController().navigate(R.id.fragment_about_us)
            }
            btnContact.setOnClickListener {
                findNavController().navigate(R.id.fragment_contacts)
            }

        }
    }

}