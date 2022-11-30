package com.example.bookers.models.gsonModels

data class Data(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
)