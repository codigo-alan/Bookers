package com.example.bookers.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookers.R
import com.example.bookers.databinding.FragmentListFavouritesBinding
import com.example.bookers.models.BookAdapter
import com.example.bookers.models.OnClickListener
import com.example.bookers.models.gsonModels.Data
import com.example.bookers.models.gsonModels.Item
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
        model.setFragment("listFavouritesFragment")
        model.data.value?.let {
            setUpRecyclerView(it)
        }
    }

    fun setUpRecyclerView(data: Data) {
        bookAdapter = BookAdapter(data.items, this) //pass param list of books and listener
        myLayoutManager = LinearLayoutManager(context)
        //.findFirstVisibleItemPosition() to get the position of the scroll, save it in viewModel
        //myLayoutManager = GridLayoutManager(context, 3) //3 columns

        binding.recyclerListFavouritesBooks.apply {
            setHasFixedSize(true) //Optimize app rendiment
            layoutManager = myLayoutManager
            adapter = bookAdapter
        }
    }

    override fun onClick(book: Item) {
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