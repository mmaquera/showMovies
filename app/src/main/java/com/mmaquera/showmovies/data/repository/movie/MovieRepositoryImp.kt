package com.mmaquera.showmovies.data.repository.movie

import com.mmaquera.showmovies.data.network.ApiService
import com.mmaquera.showmovies.domain.Movie
import com.mmaquera.showmovies.domain.MovieDetail
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor (private val apiService: ApiService): MovieRepository{

    override suspend fun getMovies(page: Int, sort: String): List<Movie> {
        return apiService.getMovies(page, sort)
    }

    override suspend fun getMoviesFilter(query: String, sort: String, page: Int): List<Movie> {
        return apiService.getMoviesFilter(query, sort, page)
    }

    override suspend fun getMovie(id: Int): MovieDetail {
        return apiService.getMovie(id)
    }
}