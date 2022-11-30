package com.example.bookers.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.bookers.R
import com.example.bookers.databinding.ActivityMainBinding
import com.example.bookers.view.fragments.DetailFragment
import com.example.bookers.view.fragments.ListFavouritesFragment
import com.example.bookers.view.fragments.ListFragment
import com.example.bookers.viewModel.BookersViewModel

class MainActivity : AppCompatActivity()  {

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
                    //model.setFragment("listFavouritesFragment")
                    addFragment(ListFavouritesFragment())
                    true
                }
                R.id.home -> {
                    addFragment(ListFragment())
                    true
                }
                R.id.search -> {
                    //TODO implementar ítem de búsqueda
                    true
                }
                else -> {
                    Toast.makeText(this, "nothing",Toast.LENGTH_SHORT).show()
                    false
                }
            }
        }
    }

    //Add fragment to activity
    fun addFragment(fragmentToPlace: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView, fragmentToPlace)
            setReorderingAllowed(true)
            addToBackStack("listFragment") // name can be null
            commit()
        }
    }


}

