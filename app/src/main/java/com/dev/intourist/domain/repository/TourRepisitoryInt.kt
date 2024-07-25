package com.dev.intourist.domain.repository

import androidx.lifecycle.LiveData
import com.dev.intourist.common.UIState
import com.dev.intourist.data.base.BaseMainResponse
import com.dev.intourist.data.remote.dtos.contacts.ContactModel
import com.dev.intourist.data.remote.dtos.tours.ToursModel

interface TourRepisitoryInt {
    suspend fun getAllTours(pageSize: Int): LiveData<UIState<ToursModel>>
    suspend fun getTourById(id:Int): LiveData<UIState<ToursModel.Result>>
}