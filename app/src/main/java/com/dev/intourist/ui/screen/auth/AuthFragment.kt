package com.dev.intourist.ui.screen.auth

import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dev.intourist.R
import com.dev.intourist.databinding.FragmentAuthBinding
import com.dev.intourist.presentation.base.fragment.BaseFragment
import com.google.android.gms.maps.GoogleMap

class AuthFragment : BaseFragment<FragmentAuthBinding, AuthViewModel>(R.layout.fragment_auth) {
//    private lateinit var binding: FragmentAuthBinding
    override val binding: FragmentAuthBinding by viewBinding(FragmentAuthBinding::bind)
    override val viewModel: AuthViewModel by viewModel()

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentAuthBinding.inflate(inflater, container, false)
//        return binding.root
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTermsText()

        binding.apply {
            etPhoneNumber.addTextChangedListener( object :TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val colorResId = if (s?.length == 13) R.color.dark_blue_for_button else R.color.light_grey
                    binding.btnContinue.backgroundTintList = ContextCompat.getColorStateList(requireContext(), colorResId) }
                override fun afterTextChanged(s: Editable?) {}
            })
            btnContinue.setOnClickListener {
                findNavController().navigate(R.id.fragment_code)
            }
            btnSkip.setOnClickListener {
                findNavController().navigate(R.id.fragment_home)
            }

            btnGoogle.setOnClickListener {
                // inter with google
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
    }

    private fun setTermsText() {
        val terms = getString(R.string.terms)
        val privacyPolicy = getString(R.string.privacy_policy)
        val text = getString(R.string.terms_and_privacy_policy)

        val spannableString = SpannableString(text)

        val termsStart = text.indexOf(terms)
        val termsEnd = termsStart + terms.length
        val termsClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {

            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = true
                ds.color = resources.getColor(R.color.logo_color)
            }
        }
        spannableString.setSpan(termsClickableSpan, termsStart, termsEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD), termsStart, termsEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        val privacyPolicyStart = text.indexOf(privacyPolicy)
        val privacyPolicyEnd = privacyPolicyStart + privacyPolicy.length
        val privacyPolicyClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {

            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = true
                ds.color = resources.getColor(R.color.logo_color)
            }
        }
        spannableString.setSpan(privacyPolicyClickableSpan, privacyPolicyStart, privacyPolicyEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD), privacyPolicyStart, privacyPolicyEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.apply {
            tvTerms.text = spannableString
            tvTerms.movementMethod = LinkMovementMethod.getInstance()
        }
    }
}