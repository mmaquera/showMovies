package com.mmaquera.showmovies.domain

sealed class Authentication{

    object Success : Authentication()
    class Error(val message: String) : Authentication()
}