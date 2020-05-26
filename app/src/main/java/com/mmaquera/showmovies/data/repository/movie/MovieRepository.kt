package com.mmaquera.showmovies.data.repository.movie

import com.mmaquera.showmovies.domain.Movie

interface MovieRepository{

    suspend fun getMovies(page: Int, sort: String): List<Movie>
    suspend fun getMoviesFilter(query: String, sort: String, page: Int): List<Movie>
}