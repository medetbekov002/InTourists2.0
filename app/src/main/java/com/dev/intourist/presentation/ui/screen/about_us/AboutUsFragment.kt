package com.dev.intourist.presentation.ui.screen.about_usimport android.os.Bundleimport android.view.Viewimport androidx.navigation.fragment.findNavControllerimport com.dev.intourist.Rimport com.dev.intourist.presentation.base.fragment.BaseFragmentimport org.koin.androidx.viewmodel.ext.android.viewModelimport by.kirich1409.viewbindingdelegate.viewBindingimport com.dev.intourist.databinding.FragmentAboutUsBindingimport com.dev.intourist.presentation.ui.screen.about_us.AboutUsViewModelimport com.google.android.gms.maps.GoogleMapclass AboutUsFragment :    BaseFragment<FragmentAboutUsBinding, AboutUsViewModel>(R.layout.fragment_about_us) {    override val binding: FragmentAboutUsBinding by viewBinding(FragmentAboutUsBinding::bind)    override val viewModel: AboutUsViewModel by viewModel()    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {        super.onViewCreated(view, savedInstanceState)        binding.apply {            titleAboutUs.setNavigationOnClickListener {                findNavController().popBackStack()            }        }    }    override fun onMapReady(googleMap: GoogleMap) {    }}