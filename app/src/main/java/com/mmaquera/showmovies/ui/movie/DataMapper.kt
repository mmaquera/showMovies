package com.mmaquera.showmovies.ui.movie

import com.mmaquera.showmovies.domain.MovieDetail
import com.mmaquera.showmovies.ui.movie.model.ActorModel
import com.mmaquera.showmovies.ui.movie.model.MovieModel
import com.mmaquera.showmovies.ui.movie.model.ReviewModel

fun MovieDetail.toModel(): MovieModel {
    return MovieModel(
        title = title,
        summary = description,
        image = image,
        cast = cast.map { ActorModel(image = it.image, name =it.actor, character = it.character) },
        reviews = reviews.map { ReviewModel(author = it.author, message = it.value) }
    )
}