package com.mmaquera.showmovies.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor() : ViewModel() {

    private val _authenticate = MutableLiveData<Boolean>()
    val authenticate : LiveData<Boolean> get() = _authenticate

    fun authenticate(email: String): String{
        return "hola"
    }
}