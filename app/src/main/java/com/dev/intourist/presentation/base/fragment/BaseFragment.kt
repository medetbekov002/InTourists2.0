package com.dev.intourist.presentation.base.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
//import com.dev.intourist.common.Either
import com.dev.intourist.common.UIState
import com.dev.intourist.data.local.preferences.Pref
import com.dev.intourist.data.utils.showToast
import com.dev.intourist.presentation.base.viewmodel.BaseViewModel
import com.google.android.gms.maps.GoogleMap
//import com.example.stylescope.common.Either
//import com.example.stylescope.common.UIState
//import com.example.stylescope.data.local.Pref
//import com.example.stylescope.presentation.model.token.GetTokenUI
//import com.example.stylescope.presentation.model.token.RefreshTokenUI
//import com.example.stylescope.presentation.model.token.VerifyTokenUI
//import com.example.stylescope.presentation.ui.token.TokenViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseFragment<Binding : ViewBinding, ViewModel : BaseViewModel>
    (@LayoutRes layoutId: Int) : Fragment(layoutId) {

    fun <T> StateFlow<UIState<T>>.stateHandler(
        fragment: Fragment,
        success: (data: T) -> Unit,
        state: ((res: UIState<T>) -> Unit)? = null
    ) {
        fragment.viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            this@stateHandler.collect { res ->
                state?.invoke(res)
                when (res) {
                    is UIState.Error -> {
                        fragment.requireContext().showToast(res.error!!)
                    }
                    is UIState.Loading -> { }
                    is UIState.Success -> {
                        res.data?.let {
                            success(it)
                        }
                    }
                    is UIState.Idle -> { }
                }
            }
        }
    }
    protected abstract val binding: Binding
    protected abstract val viewModel: ViewModel
    private val pref: Pref by lazy { Pref(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        constructListeners()
        launchObservers()
    }

    protected open fun initialize() {}

    protected open fun constructListeners() {}

    protected open fun launchObservers() {}

    protected fun <T> StateFlow<UIState<T>>.spectateUiState(
        lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
        success: ((data: T) -> Unit)? = null,
        loading: ((data: UIState.Loading<T>) -> Unit)? = null,
        error: ((error: String) -> Unit)? = null,
        idle: ((idle: UIState.Idle<T>) -> Unit)? = null,
        gatherIfSucceed: ((state: UIState<T>) -> Unit)? = null,
    ) {
        safeFlowGather(lifecycleState) {
            collect {
                gatherIfSucceed?.invoke(it)
                when (it) {
                    is UIState.Idle -> idle?.invoke(it)
                    is UIState.Loading -> loading?.invoke(it)
                    is UIState.Error -> error?.invoke(it.error)
                    is UIState.Success -> success?.invoke(it.data)
                }
            }
        }
    }

    fun safeFlowGather(
        lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
        gather: suspend () -> Unit,
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(lifecycleState) {
                gather()
            }
        }
    }

    fun <T> Flow<Either<String, T>>.safeFlowGather(
        actionIfEitherIsRight: suspend (T) -> Unit,
        actionIfEitherIsLeft: (error: String) -> Unit,
    ) {
        safeFlowGather {
            collect {
                when (it) {
                    is Either.Right -> actionIfEitherIsRight(it.value)
                    is Either.Left -> actionIfEitherIsLeft(it.value)
                }
            }
        }
    }

    abstract fun onMapReady(googleMap: GoogleMap)
}