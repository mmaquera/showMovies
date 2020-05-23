package com.mmaquera.showmovies.data.network

import com.mmaquera.showmovies.domian.Credentials
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{

    @GET("key")
    suspend fun getKey(@Query("email") email: String) : Credentials
}