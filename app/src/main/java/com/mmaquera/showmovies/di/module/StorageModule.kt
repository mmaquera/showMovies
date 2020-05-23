package com.mmaquera.showmovies.di.module

import com.mmaquera.showmovies.data.storage.SharePreferenceStorage
import com.mmaquera.showmovies.data.storage.Storage
import dagger.Binds
import dagger.Module

@Module
abstract class StorageModule{

    @Binds
    abstract fun provideStorage(storage: SharePreferenceStorage): Storage
}