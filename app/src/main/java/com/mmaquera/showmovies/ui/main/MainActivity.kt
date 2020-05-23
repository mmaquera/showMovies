package com.mmaquera.showmovies.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mmaquera.showmovies.R
import com.mmaquera.showmovies.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
