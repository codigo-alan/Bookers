package com.example.bookers.models

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.bookers.R
import com.example.bookers.databinding.ItemBookBinding


class BookAdapter(private val books: List<Book>, private val listener: OnClickListener): RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    private lateinit var context: Context

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemBookBinding.bind(view)
        fun setListener(book: Book){
            binding.root.setOnClickListener {
                listener.onClick(book)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books[position]
        with(holder){
            setListener(book)
            binding.bookTitleTv.text = book.title
            Glide.with(context)
                .load(book.image)
                .diskCacheStrategy(DiskCacheStrategy.ALL) //save in cache to avoid unneeded resources consume
                .centerCrop()
                .circleCrop()
                .into(binding.bookImageIv) //put the image in te image view
        }

    }

    override fun getItemCount(): Int {
        return books.size
    }
}
