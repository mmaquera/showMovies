package com.mmaquera.showmovies.data.repository.login

import com.mmaquera.showmovies.data.network.ApiService
import javax.inject.Inject

class LoginRepositoryImp @Inject constructor (private val apiService: ApiService) : LoginRepository{

    override suspend fun authenticate(email: String)  = apiService.getKey(email)
}