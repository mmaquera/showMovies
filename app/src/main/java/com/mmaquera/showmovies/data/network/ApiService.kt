package com.mmaquera.showmovies.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{

    @GET("key")
    fun getKey(@Query("email") email: String)
}