package com.mmaquera.showmovies.di.subcomponents

import com.mmaquera.showmovies.di.annotation.ActivityScope
import com.mmaquera.showmovies.ui.main.view.MainActivity
import com.mmaquera.showmovies.ui.main.view.MainFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: MainFragment)
}