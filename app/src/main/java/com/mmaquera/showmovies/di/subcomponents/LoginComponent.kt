package com.mmaquera.showmovies.di.subcomponents

import com.mmaquera.showmovies.ui.login.LoginActivity
import dagger.Subcomponent

@Subcomponent
interface LoginComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): LoginComponent
    }

    fun inject(activity: LoginActivity)
}