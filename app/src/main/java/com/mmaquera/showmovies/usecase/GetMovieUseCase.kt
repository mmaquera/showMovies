package com.mmaquera.showmovies.usecase

import android.util.Log
import com.mmaquera.showmovies.data.repository.movie.MovieRepositoryImp
import com.mmaquera.showmovies.domain.Response
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(private val movieRepositoryImp: MovieRepositoryImp) {

    suspend fun invoke(id: Int): Response {
        return try {
            val response = movieRepositoryImp.getMovie(id)
            Response.Success(response)
        } catch (e: Exception) {
            Log.d("error", e.message.toString())
            Response.Error(e.message.toString())
        }
    }
}