package com.mmaquera.showmovies.di.component

import com.mmaquera.showmovies.di.subcomponents.LoginComponent
import com.mmaquera.showmovies.di.subcomponents.MainComponent
import dagger.Module

@Module(subcomponents = [LoginComponent::class, MainComponent::class])
interface AppSubComponents