package com.mmaquera.showmovies.di.component

import com.mmaquera.showmovies.di.subcomponents.LoginComponent
import dagger.Module

@Module(subcomponents = [LoginComponent::class])
interface AppSubComponents