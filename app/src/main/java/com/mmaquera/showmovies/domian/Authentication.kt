package com.mmaquera.showmovies.domian

sealed class Authentication{

    object Success : Authentication()
    class Error(val message: String) : Authentication()
}