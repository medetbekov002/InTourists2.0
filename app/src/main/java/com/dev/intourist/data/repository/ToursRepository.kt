package com.dev.intourist.data.repository

import com.dev.intourist.common.UIState
import com.dev.intourist.data.base.BaseRepository
import com.dev.intourist.data.remote.dtos.tours.ToursModel
import com.dev.intourist.data.remote.service.ApiService
import com.dev.intourist.domain.repository.TourRepositoryInt
import kotlinx.coroutines.flow.StateFlow

class ToursRepository(private val apiService: ApiService) : BaseRepository() {
/*

    override suspend fun getAllTours(pageSize: Int): StateFlow<UIState<ToursModel>> = performRequest {
        apiService.getAllTours(pageSize = pageSize)
    }

    override suspend fun getTourById(id: Int): StateFlow<UIState<ToursModel.Result>> =
        performRequest {
            apiService.getTourById(id)
        }
*/

}