package com.example.bookers.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.bookers.R
import com.example.bookers.database.BookEntity
import com.example.bookers.databinding.ItemBookBinding
import com.example.bookers.models.Repository
import com.example.bookers.models.gsonModels.Item


class BookAdapter(private var books: List<Item>, private val listener: OnClickListener): RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    private lateinit var context: Context
    private val repository = Repository()

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemBookBinding.bind(view)

        fun setListener(book: Item){
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
            verifyFavorite(book, binding)
            binding.bookTitleTv.text = book.volumeInfo.title
            //val image: String = book.volumeInfo.imageLinks.smallThumbnail ?: "not linked"
            Glide.with(context)
                .load("todo")
                .diskCacheStrategy(DiskCacheStrategy.ALL) //save in cache to avoid unneeded resources consume
                .centerCrop()
                .circleCrop()
                .into(binding.bookImageIv) //put the image in te image view
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun verifyFavorite(book: Item, binding: ItemBookBinding) {
        if (book.isFavorite) binding.ivHeart.setImageResource(R.drawable.ic_favorite_item_24)
        else binding.ivHeart.setImageResource(R.drawable.ic_favorite_border_24)
        binding.ivHeart.setOnClickListener {
            book.isFavorite = !book.isFavorite
            val bookEntity: BookEntity = bookToEntity(book)
            if (book.isFavorite){
                //TODO add to room
                repository.insertBookToDb(bookEntity)
            }
            else {
                //TODO delete from room
                repository.deleteBookFromDb(bookEntity)
            }
            Log.d("fav","${book.isFavorite}")
            notifyDataSetChanged()
        }

    }

    private fun bookToEntity(book: Item): BookEntity {
        return BookEntity(
            book.id,book.isFavorite,book.volumeInfo.title,book.volumeInfo.description
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setBooks(newListBooks: List<Item>) {
        books = newListBooks
        notifyDataSetChanged() //it`s like a refresh
    }

    override fun getItemCount(): Int {
        return books.size
    }
}
