package com.mmaquera.showmovies.data.repository.movie

import com.mmaquera.showmovies.data.network.ApiService
import com.mmaquera.showmovies.domain.Movie
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor (private val apiService: ApiService): MovieRepository{

    override suspend fun getMovies(): List<Movie> {
        return apiService.getMovies()
    }
}