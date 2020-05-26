package com.mmaquera.showmovies.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.mmaquera.showmovies.R
import com.mmaquera.showmovies.application.AppApplication
import com.mmaquera.showmovies.databinding.ActivityMainBinding
import com.mmaquera.showmovies.di.subcomponents.MainComponent

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mainComponent: MainComponent

    private val navController by lazy { findNavController(R.id.navHostFragment) }
    private val appBarConfiguration = AppBarConfiguration
        .Builder(R.id.mainFragment)
        .setFallbackOnNavigateUpListener { super.onSupportNavigateUp() }
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        mainComponent = (application as AppApplication).appComponent.mainComponent().create()
        mainComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbar)
        setContentView(binding.root)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}
