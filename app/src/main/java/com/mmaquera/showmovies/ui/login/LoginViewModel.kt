package com.mmaquera.showmovies.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmaquera.showmovies.domian.Authentication
import com.mmaquera.showmovies.usecase.LoginUserCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginUserCase: LoginUserCase) : ViewModel() {

    private val _authenticate = MutableLiveData<Boolean>()
    val authenticate: LiveData<Boolean> get() = _authenticate

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun authenticate(email: String) {

        if (!validateInput(email)) {
            _errorMessage.value = "Email invalido"
            return
        }

        viewModelScope.launch {
            when (val result = withContext(Dispatchers.IO) { loginUserCase.invoke(email) }) {
                is Authentication.Success -> _authenticate.value = true
                is Authentication.Error -> _errorMessage.value = result.message
            }
        }
    }

    private fun validateInput(email: String): Boolean {
        return if (email.isEmpty()) {
            false
        } else {
            android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }
}