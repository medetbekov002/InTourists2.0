package com.dev.intourist.presentation.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.dev.intourist.domain.core.NetworkError
import com.dev.intourist.presentation.extensions.UIState
import com.dev.intourist.presentation.extensions.reset
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * Base class for all [ViewModel]s
 *
 * @author London
 */
abstract class BaseViewModel : ViewModel() {

    /**
     * Collect network request result without mapping for primitive types
     *
     * @receiver [collectEither]
     */
    protected fun <T> Flow<Either<NetworkError, T>>.collectNetworkRequest(
        state: MutableStateFlow<UIState<T>>,
        resetStateAfterCollect: Boolean = false
    ) = collectEither(state, resetStateAfterCollect) {
        UIState.Success(it)
    }

    /**
     * Collect network request result with mapping
     *
     * @receiver [collectEither]
     */
    protected fun <T, S> Flow<Either<NetworkError, T>>.collectNetworkRequest(
        state: MutableStateFlow<UIState<S>>,
        resetStateAfterCollect: Boolean = false,
        mapToUI: (T) -> S
    ) = collectEither(state, resetStateAfterCollect) {
        UIState.Success(mapToUI(it))
    }

    /**
     * Collect network request result and mapping [Either] to [UIState]
     *
     * @receiver [NetworkError] or [data][T] in [Flow] with [Either]
     *
     * @param T domain layer model
     * @param S presentation layer model
     * @param state [MutableStateFlow] with [UIState]
     *
     * @see viewModelScope
     * @see launch
     * @see [Flow.collect]
     */
    private fun <T, S> Flow<Either<NetworkError, T>>.collectEither(
        state: MutableStateFlow<UIState<S>>,
        resetStateAfterCollect: Boolean,
        successful: (T) -> UIState.Success<S>
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            state.value = UIState.Loading()
            this@collectEither.collect {
                when (it) {
                    is Either.Left -> state.value = UIState.Error(it.value)
                    is Either.Right -> state.value = successful(it.value)
                }
            }
            if (resetStateAfterCollect) {
                state.reset()
            }
        }
    }

    /**
     * Collect local request to database with mapping
     *
     * @receiver [T] with [Flow]
     *
     * @param T domain layer model
     * @param S presentation layer model
     * @param mapToUI high-order function for setup mapper functions
     */
    protected fun <T, S> Flow<T>.collectLocalRequest(
        mapToUI: (T) -> S
    ): Flow<S> = map { value: T ->
        mapToUI(value)
    }

    /**
     * Collect local request to database with mapping with [List]
     *
     * @receiver [T] in [List] with [Flow]
     *
     * @param T domain layer model
     * @param S presentation layer model
     * @param mapToUI high-order function for setup mapper functions
     */
    protected fun <T, S> Flow<List<T>>.collectLocalRequestForList(
        mapToUI: (T) -> S
    ): Flow<List<S>> = map { value: List<T> ->
        value.map { data: T ->
            mapToUI(data)
        }
    }

    /**
     * Collect paging request with mapping
     *
     * @receiver [PagingData] with [T] in [Flow]
     *
     * @param T domain layer model
     * @param S presentation layer model
     * @param mapToUI high-order function for setup mapper function
     *
     * @see cachedIn
     * @see viewModelScope
     */
    protected fun <T : Any, S : Any> Flow<PagingData<T>>.collectPagingRequest(
        mapToUI: (T) -> S
    ): Flow<PagingData<S>> = map { value: PagingData<T> ->
        value.map { data: T ->
            mapToUI(data)
        }
    }.cachedIn(viewModelScope)
}