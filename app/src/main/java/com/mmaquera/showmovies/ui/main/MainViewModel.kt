package com.mmaquera.showmovies.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmaquera.showmovies.di.settings.GlideSettingsUrl
import com.mmaquera.showmovies.domain.Movie
import com.mmaquera.showmovies.domain.Response
import com.mmaquera.showmovies.extensions.plusAssign
import com.mmaquera.showmovies.ui.main.model.FilterModel
import com.mmaquera.showmovies.ui.main.model.MovieModel
import com.mmaquera.showmovies.usecase.GetFiltersUseCase
import com.mmaquera.showmovies.usecase.GetMoviesFilterUseCase
import com.mmaquera.showmovies.usecase.GetMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class MainViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getMoviesFilterUseCase: GetMoviesFilterUseCase,
    private val glideSettingsUrl: GlideSettingsUrl,
    private val getFiltersUseCase: GetFiltersUseCase
) : ViewModel() {

    private val _movies = MutableLiveData<MutableList<MovieModel>>()
    val movies: LiveData<MutableList<MovieModel>> get() = _movies

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _filters = MutableLiveData<List<FilterModel>>()
    val filters: LiveData<List<FilterModel>> get() = _filters

    fun getMovies(page: Int, orderBy: String) {
        viewModelScope.launch {
            when (val result =
                withContext(Dispatchers.IO) { getMoviesUseCase.invoke(page, orderBy) }) {
                is Response.Success -> {
                    if (page > 1)
                        _movies += (result.response as List<Movie>).toModel()
                    else _movies.value =
                        (result.response as List<Movie>).toModel().toMutableList()
                }
                is Response.Error -> _errorMessage.value = result.message
            }
        }
    }

    fun getMoviesFilter(title: String, orderBy: String, page: Int) {
        viewModelScope.launch {
            when (val result = withContext(Dispatchers.IO) {
                getMoviesFilterUseCase.invoke(
                    title,
                    orderBy,
                    page
                )
            }) {
                is Response.Success -> {
                    _movies.value = (result.response as List<Movie>).toModel().toMutableList()
                }
                is Response.Error -> _errorMessage.value = result.message
            }
        }
    }

    fun getGlideSettings() = glideSettingsUrl

    fun getFilters() {
        _filters.value = getFiltersUseCase.invoke().toModel()
    }

}