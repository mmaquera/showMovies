package com.mmaquera.showmovies.di.subcomponents

import com.mmaquera.showmovies.ui.movie.MovieActivity
import dagger.Subcomponent

@Subcomponent
interface MovieComponent{

    @Subcomponent.Factory
    interface Factory{
        fun create(): MovieComponent
    }

    fun inject(activity: MovieActivity)
}