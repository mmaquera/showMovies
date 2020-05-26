package com.mmaquera.showmovies.ui.main.model

data class MovieModel(val id: Int, val title: String, val image: String?, val description: String){

    fun getDescriptionResume(): String{
        return if (description.length > 280) "${description.substring(0..280)}..."  else description
    }
}