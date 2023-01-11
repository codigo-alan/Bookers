package com.example.bookers.models.gsonModels


data class VolumeInfo(
    val allowAnonLogging: Boolean = false,
    val authors: List<String> = listOf(),
    val canonicalVolumeLink: String = "",
    val categories: List<String> = listOf(),
    val comicsContent: Boolean = false,
    val contentVersion: String = "",
    val description: String = "",
    //val imageLinks: ImageLinks,
    //val industryIdentifiers: List<IndustryIdentifier>,
    val infoLink: String = "",
    val language: String = "",
    val maturityRating: String = "",
    val pageCount: Int = -1,
    //val panelizationSummary: PanelizationSummary,
    val previewLink: String = "",
    val printType: String = "",
    val publishedDate: String = "",
    val publisher: String = "",
    //val readingModes: ReadingModes,
    val title: String = ""
)