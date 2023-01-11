package com.example.bookers.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.bookers.R
import com.example.bookers.adapters.BookAdapter
import com.example.bookers.database.BookEntity
import com.example.bookers.databinding.FragmentDetailBinding
import com.example.bookers.models.gsonModels.Item
import com.example.bookers.viewModel.BookersViewModel


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val model: BookersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.setFragment("detailFragment")
        verifyFavorite(model.selectedBook.value!!, binding)
        binding.bookTitleTv.text = model.selectedBook.value!!.volumeInfo.title
        binding.bookDescriptionTv.text = model.selectedBook.value!!.volumeInfo.description
        val listAuthors = model.selectedBook.value!!.volumeInfo.authors
        val listOfCategories = model.selectedBook.value!!.volumeInfo.categories
        binding.authorsTv?.text = fillAuthors(listAuthors)
        binding.categoriesTv!!.text = fillCategories(listOfCategories)

        //val link: String = model.selectedBook.value!!.volumeInfo.imageLinks.smallThumbnail
        /*Glide.with(this)
            .load(model.selectedBook.value!!.volumeInfo.imageLinks.thumbnail)
            .diskCacheStrategy(DiskCacheStrategy.ALL) //save in cache to avoid unneeded resources consume
            .centerCrop()
            .circleCrop()
            .into(binding.bookImageIv) //put the image in te image view*/
        /*Picasso.get()
            .load(link)
            .resize(50, 50)
            .centerCrop()
            .into(binding.bookImageIv)*/
    }

    //@SuppressLint("NotifyDataSetChanged")
    private fun verifyFavorite(book: Item, binding: FragmentDetailBinding) {
        if (book.isFavorite) binding.ivHeart?.setImageResource(R.drawable.ic_favorite_item_24)
        else binding.ivHeart?.setImageResource(R.drawable.ic_favorite_border_24)
        binding.ivHeart?.setOnClickListener {
            book.isFavorite = !book.isFavorite
            val bookEntity: BookEntity = bookToEntity(book)
            if (book.isFavorite){
                //TODO add to room
                model.repository.insertBookToDb(bookEntity)
            }
            else {
                //TODO delete from room
                model.repository.deleteBookFromDb(bookEntity)
            }
            Log.d("fav","${book.isFavorite}")

            //notifyDataSetChanged()
        }

    }


    private fun bookToEntity(book: Item): BookEntity {
        return BookEntity(
            book.id,book.isFavorite,book.volumeInfo.title,book.volumeInfo.description
        )
    }

    private fun fillCategories(listOfCategories: List<String>?): String {
        var categoriesString = ""
        listOfCategories?.forEach {
            categoriesString += it
        }
        return categoriesString
    }

    private fun fillAuthors(listAuthors: List<String>?): String {
        var authorsString = ""
        listAuthors?.forEach {
            authorsString += it
        }
        return authorsString
    }

}