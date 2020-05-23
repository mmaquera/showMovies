package com.mmaquera.showmovies.data.repository.login

import com.mmaquera.showmovies.data.network.ApiService

class LoginDataRepository(private val apiService: ApiService) : LoginRepository{

    override fun authenticate(email: String) {
        TODO("Not yet implemented")
    }
}