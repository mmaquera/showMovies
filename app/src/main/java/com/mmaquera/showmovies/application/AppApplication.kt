package com.mmaquera.showmovies.application

import android.app.Application
import com.mmaquera.showmovies.di.component.AppComponent
import com.mmaquera.showmovies.di.component.DaggerAppComponent

open class AppApplication: Application(){

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent():AppComponent{
        return DaggerAppComponent.factory().create(applicationContext)
    }
}