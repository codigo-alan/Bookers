package com.example.bookers.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookers.R
import com.example.bookers.databinding.FragmentListFavouritesBinding
import com.example.bookers.models.Book
import com.example.bookers.models.BookAdapter
import com.example.bookers.models.OnClickListener
import com.example.bookers.viewModel.BookersViewModel


class ListFavouritesFragment : Fragment(), OnClickListener {

    private lateinit var bookAdapter: BookAdapter
    private lateinit var myLayoutManager: RecyclerView.LayoutManager
    private val model: BookersViewModel by activityViewModels()
    lateinit var binding: FragmentListFavouritesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListFavouritesBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        model.setFragment(this)
        bookAdapter = BookAdapter(model.bookListFavourites.value!!, this)
        myLayoutManager = GridLayoutManager(context, 3) //3 columns

        binding.recyclerListFavouritesBooks.apply {
            setHasFixedSize(true)
            layoutManager = myLayoutManager
            adapter = bookAdapter
        }
    }

    override fun onClick(book: Book) {
        model.select(book)

        //navigate to detail fragment
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView, DetailFragment())
            setReorderingAllowed(true)
            addToBackStack(null)
            commit()
        }

    }
}