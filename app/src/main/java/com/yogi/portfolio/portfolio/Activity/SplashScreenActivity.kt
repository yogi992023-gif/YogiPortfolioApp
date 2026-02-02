package com.yogi.portfolio.portfolio.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.withCreated
import com.yogi.portfolio.R
import com.yogi.portfolio.databinding.ActivitySplashScreenBinding
import com.yogi.portfolio.portfolio.Analytics.AnalyticsHelper
import com.yogi.portfolio.portfolio.ViewModel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private val viewModel: AuthViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            delay(2000)
            if (viewModel.isLoggedIn()) {
                startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            } else {
                startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
            }

            finish()
        }

       /* lifecycleScope.launch(Dispatchers.IO){
            println("Dispatchers Start")
            withContext(Dispatchers.Main){
                binding.logoImage.visibility = View.GONE
            }
        }*/


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

    suspend fun work(){
      //  delay(5000)
        println("Work done")
    }
}