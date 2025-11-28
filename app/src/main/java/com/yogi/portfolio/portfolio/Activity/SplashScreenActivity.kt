package com.yogi.portfolio.portfolio.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.yogi.portfolio.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)


        lifecycleScope.launch {
            delay(2000) // 2 seconds
            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            finish() // Close splash so user can't go back to it
        }
    }
}