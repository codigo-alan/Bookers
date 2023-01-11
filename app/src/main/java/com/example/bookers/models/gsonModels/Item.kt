package com.example.bookers.models.gsonModels



data class Item(
    val etag: String = "",
    val id: String,
    val kind: String = "",
    val selfLink: String = "",
    val volumeInfo: VolumeInfo,
    var isFavorite: Boolean = false
)