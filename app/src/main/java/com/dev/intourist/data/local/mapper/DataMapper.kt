package com.dev.intourist.data.local.mapper

interface DataMapper<T> {
    fun toDomain(): T

}