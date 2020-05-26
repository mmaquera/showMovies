package com.mmaquera.showmovies.di.component

import com.mmaquera.showmovies.di.subcomponents.LoginComponent
import com.mmaquera.showmovies.di.subcomponents.MainComponent
import com.mmaquera.showmovies.di.subcomponents.MovieComponent
import dagger.Module

@Module(subcomponents = [LoginComponent::class, MainComponent::class, MovieComponent::class])
interface AppSubComponents