package com.mmaquera.showmovies.di.module

import androidx.lifecycle.ViewModel
import com.mmaquera.showmovies.di.annotation.ViewModelKey
import com.mmaquera.showmovies.ui.login.LoginViewModel
import com.mmaquera.showmovies.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule{

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel) : ViewModel
}