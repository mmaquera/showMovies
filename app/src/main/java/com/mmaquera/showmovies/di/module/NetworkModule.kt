package com.mmaquera.showmovies.di.module

import com.mmaquera.showmovies.data.network.ApiService
import com.mmaquera.showmovies.data.storage.Constants
import com.mmaquera.showmovies.data.storage.Storage
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
    fun providerGsonProvider() = GsonConverterFactory.create()

    @Provides
    fun providerOKHttpClient(storage: Storage): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
            .readTimeout(1200, TimeUnit.SECONDS)
            .connectTimeout(1200, TimeUnit.SECONDS)

        clientBuilder.addInterceptor { chain ->
            val req = chain.request().newBuilder()
            req.addHeader("api-key", storage.getString(Constants.TOKEN))
            chain.proceed(req.build())
        }
        return clientBuilder.build()
    }

    @Provides
    fun providerRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
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