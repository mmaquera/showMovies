package com.mmaquera.showmovies.domain

sealed class Response{

    class Success(val response: Any): Response()
    class Error(val message: String) : Response()
}