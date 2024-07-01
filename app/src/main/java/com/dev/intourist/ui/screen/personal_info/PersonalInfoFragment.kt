package com.dev.intourist.ui.screen.personal_info

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dev.intourist.R
import com.dev.intourist.data.local.preferences.PreferencesClient
import com.dev.intourist.databinding.FragmentPersonalInfoBinding
import com.dev.intourist.presentation.base.fragment.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class PersonalInfoFragment :
    BaseFragment<FragmentPersonalInfoBinding, PersonalInfoViewModel>(R.layout.fragment_personal_info) {

    override val binding: FragmentPersonalInfoBinding by viewBinding(FragmentPersonalInfoBinding::bind)
    override val viewModel: PersonalInfoViewModel by viewModel()
    lateinit var preferencesClient: PreferencesClient

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

//    override fun initSubscribers() {
//        super.initSubscribers()
//        viewModel.accountDeletionState.collectUiStateBema(
//            onSuccess = {
//                tokenViewModel.deleteToken()
//                showToast("Успешно удалено")
//            },
//            onError = {
//                showToast(it)
//            }
//        )
//    }
//
//    override fun initListeners() {
//        super.initListeners()
//        setBottomSheet()
//        clickers()
//    }
//
//    override fun initialize() {
//        super.initialize()
//        binding.tvName.hint = "ФИО"
//        getSharedData()
//    }
//
//    private fun getSharedData() {
//        val pref = activity?.getSharedPreferences("preferences_profile", Context.MODE_PRIVATE)
//        val id = pref?.getString("fio", "")
//        binding.tvName.text = id
//    }
//
//    private fun clickers() {
//        with(binding) {
//            btnProfile.setOnClickListener {
//                findNavController().navigate(R.id.fillProfileFragment)
//            }
//        }
//    }
//
//    private fun setBottomSheet() {
//        binding.btnExit.setOnClickListener {
//            val view: View = layoutInflater.inflate(R.layout.exit_bottom_sheet, null)
//            val dialog = BottomSheetDialog(requireActivity())
//            dialog.setContentView(view)
//            val btnDismiss = dialog.findViewById<Button>(R.id.btn_back)
//            val btnLeave = dialog.findViewById<Button>(R.id.btn_confirm)
//
//            btnDismiss?.setOnClickListener {
//                dialog.dismiss()
//            }
//            btnLeave?.setOnClickListener {
//                onLogOut()
//            }
//            dialog.show()
//        }

}