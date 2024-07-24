package com.dev.intourist.data.remote.dtos.contacts

data class ContactModel(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Result>
) {
    data class Result(
        val id: Int,
        val instagram_link: String,
        val telegram_link: String,
        val whatsapp_link: String
    )
}