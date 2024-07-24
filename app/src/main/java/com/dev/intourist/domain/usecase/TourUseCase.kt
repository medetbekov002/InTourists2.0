package com.dev.intourist.domain.usecase

import com.dev.intourist.domain.repository.TourRepisitoryInt

class TourUseCase(private val toursRepositoryInt: TourRepisitoryInt) {

    suspend fun getAllTours() = toursRepositoryInt.getAllTours()
    suspend fun getTourById(id:Int) = toursRepositoryInt.getTourById(id)

}