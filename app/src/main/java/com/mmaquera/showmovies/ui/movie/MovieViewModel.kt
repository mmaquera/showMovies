package com.mmaquera.showmovies.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmaquera.showmovies.di.settings.GlideSettingsUrl
import com.mmaquera.showmovies.domain.MovieDetail
import com.mmaquera.showmovies.domain.Response
import com.mmaquera.showmovies.ui.movie.model.MovieModel
import com.mmaquera.showmovies.usecase.GetMovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase,
    private val glideSettingsUrl: GlideSettingsUrl
) :
    ViewModel() {

    private val _movie = MutableLiveData<MovieModel>()
    val movie: LiveData<MovieModel> get() = _movie

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun getMovie(id: Int) {
        viewModelScope.launch {
            when (val result = withContext(Dispatchers.IO) { getMovieUseCase.invoke(id) }) {
                is Response.Success -> {
                    _movie.value = (result.response as MovieDetail).toModel()
                }
                is Response.Error -> _errorMessage.value = result.message
            }
        }
    }

    fun getGlideSettings() = glideSettingsUrl
}