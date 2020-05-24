package com.mmaquera.showmovies.ui.main.movies

import com.mmaquera.showmovies.domain.Movie
import com.mmaquera.showmovies.ui.main.model.MovieModel

fun List<Movie>.toModel() : List<MovieModel>{
    return map {
        MovieModel(
            id = it.id,
            title = it.title,
            description = it.description,
            image = it.image
        )
    }
}