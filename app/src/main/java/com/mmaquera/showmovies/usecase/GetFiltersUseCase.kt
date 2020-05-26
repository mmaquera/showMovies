package com.mmaquera.showmovies.usecase

import javax.inject.Inject

class GetFiltersUseCase @Inject constructor() {

    private val filters = listOf(
        Data("title.asc","title.asc",true),
        Data("title.desc","title.desc",false),
        Data("date.asc","date.asc",false),
        Data("date.desc","date.desc",false),
        Data("popularity.asc","popularity.asc",false),
        Data("popularity.desc","popularity.desc",false)
    )

    fun invoke(): List<Data> {
        return filters
    }

    data class Data(val id: String, val name: String, val status: Boolean)
}