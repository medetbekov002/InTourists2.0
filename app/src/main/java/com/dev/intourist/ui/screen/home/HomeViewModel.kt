package com.dev.intourist.ui.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev.intourist.common.UIState
import com.dev.intourist.data.base.BaseMainResponse
import com.dev.intourist.data.remote.dtos.tours.ToursModel
import com.dev.intourist.domain.usecase.TourUseCase
import com.dev.intourist.presentation.base.viewmodel.BaseViewModel

class HomeViewModel(private val useCase: TourUseCase): BaseViewModel() {

    suspend fun getAllTours(pageSize: Int): LiveData<UIState<ToursModel>> = useCase.getAllTours(pageSize = pageSize)
}