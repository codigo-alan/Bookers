package com.example.bookers.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookers.R
import com.example.bookers.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        binding.backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

    }
}