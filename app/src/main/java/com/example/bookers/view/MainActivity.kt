package com.example.bookers.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookers.R
import com.example.bookers.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Ad fragment to activity
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView, ListFragment())
            setReorderingAllowed(true)
            addToBackStack("listFragment") // name can be null
            commit()
        }


    }
}