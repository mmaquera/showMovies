package com.mmaquera.showmovies.ui.main

import com.mmaquera.showmovies.domain.Movie
import com.mmaquera.showmovies.ui.main.model.FilterModel
import com.mmaquera.showmovies.ui.main.model.MovieModel
import com.mmaquera.showmovies.usecase.GetFiltersUseCase

fun List<Movie>.toModel(): List<MovieModel> {
    return map {
        MovieModel(
            id = it.id,
            title = it.title,
            description = it.description,
            image = it.image
        )
    }
}

@JvmName("filterToModel")
fun List<GetFiltersUseCase.Data>.toModel(): List<FilterModel> {
    return map {
        FilterModel(it.id, it.name, it.status)
    }
}