package com.mmaquera.showmovies.data.repository.movie

import com.mmaquera.showmovies.domain.Movie

interface MovieRepository{

    suspend fun getMovies(): List<Movie>
}