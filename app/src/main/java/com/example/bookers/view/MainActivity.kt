package com.example.bookers.view

import android.app.SearchManager
import android.content.Context
import android.content.Context.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import com.example.bookers.R
import com.example.bookers.databinding.ActivityMainBinding
import com.example.bookers.view.fragments.DetailFragment
import com.example.bookers.view.fragments.ListFavouritesFragment
import com.example.bookers.view.fragments.ListFragment
import com.example.bookers.viewModel.BookersViewModel

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener  {

    lateinit var binding: ActivityMainBinding
    private val model: BookersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO eliminar navigationIcon, o crearlo cuando haga falta e implementarlo

        //TODO mejorar agregado de fragment según string de viewModel

        val fragmentToPlace = when (model.actualFragment.value!!) {
            "listFragment" -> ListFragment()
            "detailFragment" -> DetailFragment()
            "listFavouritesFragment" -> ListFavouritesFragment()
            else -> ListFragment()
        }
        addFragment(fragmentToPlace)

        //Manage clicks on tool bar
        binding.materialToolbar!!.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.favorite -> {
                    addFragment(ListFavouritesFragment())
                    true
                }
                R.id.home -> {
                    addFragment(ListFragment())
                    true
                }
                R.id.search -> {
                    val searchView = it.actionView as SearchView
                    searchView.setOnQueryTextListener(this)
                    true
                }
                else -> false
            }
        }
    }

    /*override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.top_app_bar_menu, menu)
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return true
    }*/


    //Add fragment to activity
    fun addFragment(fragmentToPlace: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView, fragmentToPlace)
            setReorderingAllowed(true)
            addToBackStack("listFragment") // name can be null
            commit()
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        model.setSearchString(query!!)
        model.fetchData(model.search.value!!)
        Log.d("search", model.search.value!!)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.d("search", model.search.value!!)
        return false
    }


}

