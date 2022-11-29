package com.example.bookers.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.bookers.databinding.FragmentDetailBinding
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
        binding.bookTitleTv.text = model.selectedBook.value!!.title
        binding.bookDescriptionTv.text = model.selectedBook.value!!.description
        Glide.with(this)
            .load(model.selectedBook.value!!.image)
            .diskCacheStrategy(DiskCacheStrategy.ALL) //save in cache to avoid unneeded resources consume
            .centerCrop()
            .circleCrop()
            .into(binding.bookImageIv) //put the image in te image view
    }

}