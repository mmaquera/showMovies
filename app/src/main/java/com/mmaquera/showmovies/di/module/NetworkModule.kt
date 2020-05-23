package com.mmaquera.showmovies.di.module

import com.mmaquera.showmovies.data.network.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule{

    @Provides
    fun providerGsonProvider() = GsonConverterFactory.create()

    @Provides
    fun providerOKHttpClient() : OkHttpClient{
        return OkHttpClient.Builder()
            .readTimeout(1200, TimeUnit.SECONDS)
            .connectTimeout(1200, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun providerRetrofit(gsonConverterFactory: GsonConverterFactory, okHttpClient: OkHttpClient): Retrofit{
        return Retrofit
            .Builder()
            .baseUrl("https://dev-candidates.wifiesta.com/")
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun providerAPIService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)
}