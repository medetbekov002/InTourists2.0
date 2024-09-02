package com.dev.intourist.presentation.ui.screen.my_toursimport androidx.lifecycle.LiveDataimport com.dev.intourist.common.UIStateimport com.dev.intourist.data.remote.dtos.tours.ToursModelimport com.dev.intourist.domain.usecase.TourUseCaseimport com.dev.intourist.presentation.base.viewmodel.BaseViewModelimport kotlinx.coroutines.flow.StateFlowclass MyToursViewModel(private val useCase: TourUseCase) : BaseViewModel() {    suspend fun getAllTours(pageSize: Int): StateFlow<UIState<ToursModel>> = useCase.getAllTours(pageSize = pageSize)}