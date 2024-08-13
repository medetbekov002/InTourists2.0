package com.dev.intourist.ui.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev.intourist.common.UIState
import com.dev.intourist.data.base.BaseMainResponse
import com.dev.intourist.data.remote.dtos.tours.ToursModel
import com.dev.intourist.domain.usecase.TourUseCase
import com.dev.intourist.presentation.base.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel(private val useCase: TourUseCase): BaseViewModel() {
   /* private val _tours = MutableStateFlow<UIState<ToursModel>>(UIState.Idle())
    val tours = _tours.asStateFlow()

    suspend fun getAllTours(pageSize: Int){
        useCase.getAllTours(pageSize).collect { uiState : UIState<ToursModel>->
            _tours.value = uiState
        }
    }*/
}