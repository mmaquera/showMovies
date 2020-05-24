package com.mmaquera.showmovies.di.subcomponents

import com.mmaquera.showmovies.di.annotation.ActivityScope
import com.mmaquera.showmovies.di.module.MainModule
import com.mmaquera.showmovies.ui.main.MainActivity
import com.mmaquera.showmovies.ui.main.detail.DetailFragment
import com.mmaquera.showmovies.ui.main.movies.MainFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: MainFragment)
    fun inject(fragment: DetailFragment)
}