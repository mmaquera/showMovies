package com.mmaquera.showmovies.usecase

import com.mmaquera.showmovies.data.repository.login.LoginRepository
import kotlin.system.measureTimeMillis

class LoginUserCase(private val loginRepository: LoginRepository){

    fun invoke(email: String){
        loginRepository.authenticate(email)
    }

}