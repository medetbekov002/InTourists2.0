package com.dev.intourist.ui.screen.payment.other_wayimport android.os.Bundleimport androidx.fragment.app.Fragmentimport android.view.LayoutInflaterimport android.view.Viewimport android.view.ViewGroupimport com.dev.intourist.Rimport com.dev.intourist.databinding.FragmentOtherWayBindingclass OtherWayFragment : Fragment() {    private val binding : FragmentOtherWayBinding by lazy {        FragmentOtherWayBinding.inflate(layoutInflater)    }    override fun onCreate(savedInstanceState: Bundle?) {        super.onCreate(savedInstanceState)    }    override fun onCreateView(        inflater: LayoutInflater, container: ViewGroup?,        savedInstanceState: Bundle?    ): View? {        // Inflate the layout for this fragment        return binding.root    }}