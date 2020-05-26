package com.mmaquera.showmovies.data.network

import com.mmaquera.showmovies.domain.Credentials
import com.mmaquera.showmovies.domain.Movie
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("key")
    suspend fun getKey(@Query("email") email: String): Credentials

    @GET("movies")
    suspend fun getMovies(@Query("page") page: Int, @Query("sort") sort: String): List<Movie>

    @GET("movies")
    suspend fun getMoviesFilter(
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int
    ): List<Movie>
}