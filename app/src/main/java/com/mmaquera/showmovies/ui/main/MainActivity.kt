package com.mmaquera.showmovies.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mmaquera.showmovies.R
import com.mmaquera.showmovies.application.AppApplication
import com.mmaquera.showmovies.databinding.ActivityMainBinding
import com.mmaquera.showmovies.di.subcomponents.MainComponent

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mainComponent: MainComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        mainComponent = (application as AppApplication).appComponent.mainComponent().create()
        mainComponent.inject(this)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
