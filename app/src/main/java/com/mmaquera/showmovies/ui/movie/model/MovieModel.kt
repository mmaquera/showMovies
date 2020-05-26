package com.mmaquera.showmovies.ui.movie.model

data class MovieModel(
    val title: String,
    val summary: String,
    val image: String?,
    val cast: List<ActorModel>,
    val reviews: List<ReviewModel>
)