package com.dev.intourist.data.remote.dtos.tours

data class ToursModel(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<Result>
) {
    data class Result(
        val category: Int,
        val description: String,
        val difficulty: Difficulty,
        val duration: Duration,
        val equipment: List<Equipment>,
        val id: Int,
        val images: List<Image>,
        val included: List<Included>,
        val not_included: List<NotIncluded>,
        val pickup_locations: List<PickupLocation>,
        val price: String,
        val program: List<Program>,
        val region_country: String,
        val tags: Tags,
        val title: String,
        val tour_dates: List<TourDate>,
        val views: Int
    ) {
        data class Difficulty(
            val id: Int,
            val title: String
        )

        data class Duration(
            val id: Int,
            val title: String
        )

        data class Equipment(
            val id: Int,
            val title: String
        )

        data class Image(
            val id: Int,
            val image: String
        )

        data class Included(
            val id: Int,
            val title: String
        )

        data class NotIncluded(
            val id: Int,
            val title: String
        )

        data class PickupLocation(
            val id: Int,
            val title: String
        )

        data class Program(
            val id: Int,
            val time: String,
            val title: String
        )

        data class Tags(
            val id: Int,
            val title: String
        )

        data class TourDate(
            val date: String,
            val id: Int
        )
    }
}