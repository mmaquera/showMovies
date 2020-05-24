package com.mmaquera.showmovies.usecase

import android.util.Log
import com.mmaquera.showmovies.data.repository.movie.MovieRepositoryImp
import com.mmaquera.showmovies.domain.Response
import java.lang.Exception
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val movieRepositoryImp: MovieRepositoryImp) {

    suspend fun invoke() : Response {
        return try {
            val response = movieRepositoryImp.getMovies()
            Response.Success(response)
        } catch (e: Exception) {
            Log.d("error", e.message.toString())
            Response.Error(e.message.toString())
        }
    }
}