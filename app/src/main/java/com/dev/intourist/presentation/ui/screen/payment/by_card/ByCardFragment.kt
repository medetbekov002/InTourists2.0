package com.dev.intourist.presentation.ui.screen.payment.by_cardimport android.os.Bundleimport android.view.Viewimport org.koin.androidx.viewmodel.ext.android.viewModelimport androidx.navigation.fragment.findNavControllerimport by.kirich1409.viewbindingdelegate.viewBindingimport com.dev.intourist.Rimport com.dev.intourist.databinding.FragmentByCardBindingimport com.dev.intourist.presentation.base.fragment.BaseFragmentimport com.google.android.gms.maps.GoogleMapclass ByCardFragment : BaseFragment<FragmentByCardBinding, ByCardViewModel>(R.layout.fragment_by_card) {    override val binding: FragmentByCardBinding by viewBinding(FragmentByCardBinding::bind)    override val viewModel: ByCardViewModel by viewModel()//    private val binding : FragmentByCardBinding by lazy {//        FragmentByCardBinding.inflate(layoutInflater)//    }    override fun onCreate(savedInstanceState: Bundle?) {        super.onCreate(savedInstanceState)    }//    override fun onCreateView(//        inflater: LayoutInflater, container: ViewGroup?,//        savedInstanceState: Bundle?//    ): View? {//        return binding.root//    }    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {        super.onViewCreated(view, savedInstanceState)        binding.apply {            buttonBack.setOnClickListener {                findNavController().popBackStack()            }            buttonContinue.setOnClickListener {                findNavController().navigate(R.id.SuccessFragment)            }        }    }    override fun onMapReady(googleMap: GoogleMap) {    }}