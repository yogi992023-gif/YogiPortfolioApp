package com.yogi.portfolio.portfolio.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.Navigation.findNavController
import com.yogi.portfolio.R
import com.yogi.portfolio.databinding.ActivityLoginBinding
import com.yogi.portfolio.portfolio.ViewModel.AuthViewModel
import com.yogi.portfolio.portfolio.ui.composable.LoginScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.composeInputContainer.setContent {
            LoginScreen(
            onLoginClick = { email, password ->
                viewModel.login(email,password)
            },
            onRegisterClick = {
                Toast.makeText(this,"Sign up clicked...", Toast.LENGTH_SHORT).show()
                // navigate to Register screen
            }
            )
        }
        viewModel.authState.observe(this) { result ->
            result.onSuccess {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }.onFailure {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
        }

        /*binding.btnLogout.setOnClickListener {
            viewModel.logout()
            findNavController().navigate(R.id.loginFragment)
        }*/


        /*binding.btnLogin.setOnClickListener {
            viewModel.login(
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
            )
        }

        viewModel.authState.observe(this) { result ->
            result.onSuccess {
             //   findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment)
            }.onFailure {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvRegister.setOnClickListener {
         //   findNavController().navigate(R.id.registerFragment)
        }*/

        /*binding.btnLogout.setOnClickListener {
            viewModel.logout()
            findNavController().navigate(R.id.loginFragment)
        }*/
    }
}