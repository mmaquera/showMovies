package com.mmaquera.showmovies.data.repository.login

import com.mmaquera.showmovies.domain.Credentials

interface LoginRepository{
    suspend fun authenticate(email: String) : Credentials
}