package com.example.bookers.adapters

import com.example.bookers.models.gsonModels.Item

interface OnClickListener {
    fun onClick(book: Item)
}