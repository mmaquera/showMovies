package com.mmaquera.showmovies.domain

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    val id: Int,
    val title: String,
    val adult: Boolean,
    val image: String?,
    val description: String,
    @SerializedName("tagline") val tagLine: String,
    @SerializedName("release_date") val releaseDate: String,
    val status: String,
    val reviews : List<Review>,
    val cast: List<Actor>
)