package com.dev.intourist.domain.usecase

import com.dev.intourist.domain.repository.TourRepisitoryInt

class TourUseCase(private val toursRepositoryInt: TourRepisitoryInt) {

    suspend fun getAllTours(pageSize: Int) = toursRepositoryInt.getAllTours(pageSize = pageSize)
    suspend fun getTourById(id:Int) = toursRepositoryInt.getTourById(id)

}