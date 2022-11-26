package com.example.bookers.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.bookers.R
import com.example.bookers.databinding.ActivityMainBinding
import com.example.bookers.view.fragments.ListFragment
import com.example.bookers.viewModel.BookersViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val model: BookersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Ad fragment to activity
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView, model.actualFragment.value!!)
            setReorderingAllowed(true)
            addToBackStack("listFragment") // name can be null
            commit()
        }


    }
}