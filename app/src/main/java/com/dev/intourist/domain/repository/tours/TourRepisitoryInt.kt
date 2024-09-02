package com.dev.intourist.domain.repository.tours

import com.dev.intourist.common.UIState
import com.dev.intourist.data.remote.dtos.tours.ToursModel
import kotlinx.coroutines.flow.StateFlow

interface TourRepositoryInt {
    suspend fun getAllTours(pageSize: Int): StateFlow<UIState<ToursModel>>
    suspend fun getTourById(id:Int): StateFlow<UIState<ToursModel.Result>>
}