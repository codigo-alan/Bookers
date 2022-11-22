package com.example.bookers.models

import kotlinx.serialization.Serializable

@Serializable
data class Book(val id: Int, val title: String, val description: String)