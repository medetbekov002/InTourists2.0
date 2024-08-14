package com.dev.intourist.ui.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.intourist.common.UIState
import com.dev.intourist.data.base.BaseMainResponse
import com.dev.intourist.data.remote.dtos.tours.ToursModel
import com.dev.intourist.domain.usecase.TourUseCase
import com.dev.intourist.presentation.base.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val useCase: TourUseCase): BaseViewModel() {
    private val _tours = MutableStateFlow<UIState<ToursModel>>(UIState.Idle())
    val tours = _tours.asStateFlow()

    fun getAllTours(pageSize: Int){
        viewModelScope.launch {
            useCase.getAllTours(pageSize).collect { uiState : UIState<ToursModel>->
                //  _tours.value = uiState
                //если что можно список добавлять в адаптере а здесь не париться
                _tours.update {state ->
                    when(state){
                        is UIState.Success -> {
                            if (uiState is UIState.Success) {
                                val updatedTours = state.data.copy(
                                    results = state.data.results + uiState.data.results
                                )
                                UIState.Success(updatedTours)
                            } else {
                                uiState
                            }
                        }
                        else -> uiState
                    }
                }
            }
        }
    }
}