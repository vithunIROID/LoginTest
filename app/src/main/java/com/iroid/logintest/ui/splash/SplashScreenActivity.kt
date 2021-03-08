package com.iroid.logintest.ui.splash

import android.content.Intent
import android.os.Build
import android.os.Bundle 
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager 
import androidx.appcompat.app.AppCompatActivity
import com.iroid.logintest.ui.login.LoginActivity
import com.iroid.logintest.R
import dagger.hilt.android.AndroidEntryPoint

class SplashScreenActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) { 
        super.onCreate(savedInstanceState) 
        setContentView(R.layout.activity_splash_screen)

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)

    }

}