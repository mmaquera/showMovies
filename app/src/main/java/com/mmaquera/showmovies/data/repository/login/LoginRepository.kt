package com.mmaquera.showmovies.data.repository.login

interface LoginRepository{
    fun authenticate(email: String)
}