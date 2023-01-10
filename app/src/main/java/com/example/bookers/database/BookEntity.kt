package com.example.bookers.database

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Data class with format to save in database
 */

@Entity(tableName = "BookEntity")
data class BookEntity (
    @PrimaryKey val id: String,
    //TODO need add each line of volumen info that use.
    // Access form favorites books will be different than Books from API.
    //val volumeInfo: VolumeInfo,
    var isFavorite: Boolean = false
)