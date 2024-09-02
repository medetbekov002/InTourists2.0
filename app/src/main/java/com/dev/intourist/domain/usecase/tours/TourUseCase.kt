package com.dev.intourist.domain.usecase.tours

import com.dev.intourist.domain.repository.tours.TourRepositoryInt

class TourUseCase(private val toursRepositoryInt: TourRepositoryInt) {

    suspend fun getAllTours(pageSize: Int) = toursRepositoryInt.getAllTours(pageSize = pageSize)
    suspend fun getTourById(id:Int) = toursRepositoryInt.getTourById(id)

}