package com.mmaquera.showmovies.data.network

import com.mmaquera.showmovies.domain.Credentials
import com.mmaquera.showmovies.domain.Movie
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService{

    @GET("key")
    suspend fun getKey(@Query("email") email: String) : Credentials

    @GET("movies")
    suspend fun getMovies(): List<Movie>
}