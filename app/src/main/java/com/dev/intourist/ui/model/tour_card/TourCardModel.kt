package com.dev.intourist.ui.model.tour_card

data class TourCardModel(
    val list: List<Int>,
    val title: String,
    val fullPrice: String,
    val price: String,
    val duration: String,
    val dates: String,
    var isLiked: Boolean,
)
