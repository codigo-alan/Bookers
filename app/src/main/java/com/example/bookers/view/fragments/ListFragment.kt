package com.example.bookers.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookers.R
import com.example.bookers.databinding.FragmentListBinding
import com.example.bookers.models.Book
import com.example.bookers.models.BookAdapter
import com.example.bookers.models.OnClickListener
import com.example.bookers.viewModel.BookersViewModel


class ListFragment : Fragment(), OnClickListener {

    private lateinit var bookAdapter: BookAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    private lateinit var binding: FragmentListBinding
    private val model: BookersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        bookAdapter = BookAdapter(model.bookList.value!!, this) //pass param list of books and listener
        linearLayoutManager = LinearLayoutManager(context)
        //.findFirstVisibleItemPosition() to get the position of the scroll, save it in viewModel
        //linearLayoutManager = GridLayoutManager(context, 3) //3 columns

        binding.recyclerListBooks.apply {
            setHasFixedSize(true) //Optimize app rendiment
            layoutManager = linearLayoutManager
            adapter = bookAdapter
        }

    }

    override fun onClick(book: Book) {
        model.select(book) //change the selected book in the view model
        //change the fragment contained in the activity
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView, DetailFragment())
            setReorderingAllowed(true)
            addToBackStack(null)
            commit()
        }

    }
}