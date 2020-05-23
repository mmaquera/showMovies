package com.mmaquera.showmovies.usecase


import com.mmaquera.showmovies.data.repository.login.LoginRepositoryImp
import com.mmaquera.showmovies.data.storage.Constants
import com.mmaquera.showmovies.data.storage.Storage
import com.mmaquera.showmovies.domian.Authentication
import javax.inject.Inject

class LoginUserCase @Inject constructor(
    private val loginRepositoryImp: LoginRepositoryImp,
    private val storage: Storage
) {

    suspend fun invoke(email: String): Authentication {
        return try {
            val response = loginRepositoryImp.authenticate(email)
            storage.setString(Constants.TOKEN, response.key)
            Authentication.Success
        } catch (e: Exception) {
            Authentication.Error(e.message.toString())
        }
    }
}