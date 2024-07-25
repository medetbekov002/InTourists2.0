package com.dev.intourist.data.repository

import androidx.lifecycle.LiveData
import com.dev.intourist.common.UIState
import com.dev.intourist.data.base.BaseMainResponse
import com.dev.intourist.data.base.BaseRepository
import com.dev.intourist.data.remote.dtos.contacts.ContactModel
import com.dev.intourist.data.remote.dtos.tours.ToursModel
import com.dev.intourist.data.remote.service.ApiService
import com.dev.intourist.domain.repository.TourRepisitoryInt

class ToursRepository(private val apiService: ApiService) : BaseRepository(), TourRepisitoryInt {

    override suspend fun getAllTours(pageSize: Int): LiveData<UIState<ToursModel>> = performRequest {
        apiService.getAllTours(pageSize = pageSize)
    }

    override suspend fun getTourById(id: Int): LiveData<UIState<ToursModel.Result>> =
        performRequest {
            apiService.getTourById(id)
        }


}