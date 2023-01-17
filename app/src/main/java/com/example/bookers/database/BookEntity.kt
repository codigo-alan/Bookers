package com.example.bookers.database

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Data class with format to save in database
 */

@Entity(tableName = "BookEntity")
data class BookEntity (
    @PrimaryKey val id: String,
    var isFavorite: Boolean = false,
    val title: String,
    val description: String,
    /*val language: String,
    val publisher: String,
    val publishedDate: String,
    val infoLink: String*/
)