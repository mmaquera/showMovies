package com.mmaquera.showmovies.ui.main.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmaquera.showmovies.di.settings.GlideSettingsUrl
import com.mmaquera.showmovies.domain.Movie
import com.mmaquera.showmovies.domain.Response
import com.mmaquera.showmovies.ui.main.model.MovieModel
import com.mmaquera.showmovies.usecase.GetMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class MainViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val glideSettingsUrl: GlideSettingsUrl
) : ViewModel() {

    private val _movies = MutableLiveData<List<MovieModel>>()
    val movies: LiveData<List<MovieModel>> get() = _movies

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun getMovies() {
        viewModelScope.launch {
            when (val result = withContext(Dispatchers.IO) { getMoviesUseCase.invoke() }) {
                is Response.Success -> {
                    _movies.value = (result.response as List<Movie>).toModel()
                }
                is Response.Error -> _errorMessage.value = result.message
            }
        }
    }

    fun getGlideSettings() = glideSettingsUrl

}