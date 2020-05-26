package com.mmaquera.showmovies.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mmaquera.showmovies.R
import com.mmaquera.showmovies.application.AppApplication
import com.mmaquera.showmovies.databinding.ActivityLoginBinding
import com.mmaquera.showmovies.ui.main.view.MainActivity
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_NoActionBar)
        (application as AppApplication).appComponent.loginComponent().create().inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupEvents()
        setupObservers()
    }

    private fun setupEvents() {
        with(binding) {
            buttonSignIn.setOnClickListener {
                viewModel.authenticate(editEmail.text.toString())
            }
        }
    }

    private fun setupObservers() {
        viewModel.authenticate.observe(this, Observer { success ->
            if (success) startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        })
        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
        })
    }
}
