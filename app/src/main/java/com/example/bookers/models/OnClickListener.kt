package com.example.bookers.models

import com.example.bookers.models.gsonModels.Item

interface OnClickListener {
    fun onClick(book: Item)
}