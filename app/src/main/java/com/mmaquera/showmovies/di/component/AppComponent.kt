package com.mmaquera.showmovies.di.component

import android.content.Context
import com.mmaquera.showmovies.di.module.AppModule
import com.mmaquera.showmovies.di.module.NetworkModule
import com.mmaquera.showmovies.di.module.StorageModule
import com.mmaquera.showmovies.di.module.ViewModelModule
import com.mmaquera.showmovies.di.subcomponents.LoginComponent
import dagger.BindsInstance
import dagger.Component

@Component(
    modules =
    [
        AppModule::class,
        AppSubComponents::class,
        ViewModelModule::class,
        NetworkModule::class,
        StorageModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun loginComponent(): LoginComponent.Factory
}