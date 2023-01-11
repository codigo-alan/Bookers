package com.example.bookers.models

import com.example.bookers.database.BookEntity
import com.example.bookers.models.gsonModels.Item
import com.example.bookers.models.gsonModels.VolumeInfo

class Caster {
    fun entityToItem(bookEntity: BookEntity): Item {
        return Item(
            id = bookEntity.id,
            volumeInfo =
            VolumeInfo(description = bookEntity.description, title = bookEntity.title)
        )

    }
    fun itemToEntity(book: Item): BookEntity {
        return BookEntity(
            book.id,book.isFavorite,book.volumeInfo.title,book.volumeInfo.description
        )
    }

}