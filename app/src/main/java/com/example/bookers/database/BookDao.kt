package com.example.bookers.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookDao {
    @Query("SELECT * FROM BookEntity")
    fun getFavoritesBooks(): MutableList<BookEntity>

    @Query("SELECT COUNT(*) FROM BookEntity WHERE id = :id")
    fun getBookById(id: String): Int

    @Insert
    fun addBook(itemEntity: BookEntity)

    @Delete
    fun deleteBook(itemEntity: BookEntity)
}