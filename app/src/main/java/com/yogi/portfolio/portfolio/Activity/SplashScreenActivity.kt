package com.yogi.portfolio.portfolio.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.yogi.portfolio.R
import com.yogi.portfolio.portfolio.Analytics.AnalyticsHelper
import com.yogi.portfolio.portfolio.ViewModel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {

    private val viewModel: AuthViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)

        lifecycleScope.launch {
            delay(2000)
            if (viewModel.isLoggedIn()) {
                startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            } else {
                startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
            }
            finish()
        }

        /*if (viewModel.isLoggedIn()) {
            lifecycleScope.launch {
                delay(2000)
                finish()
            }
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            lifecycleScope.launch {
                delay(2000)
                finish()
            }
            startActivity(Intent(this, LoginActivity::class.java))
        }*/

    }
}