package com.bamboogeeks.julyacademy2.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.bamboogeeks.julyacademy2.R
import com.bamboogeeks.julyacademy2.auth.LoginScreen

@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()

        Handler().postDelayed({
            // Navigate from this class to Main Activity Class
           val intent =  Intent(this, LoginScreen::class.java)
            startActivity(intent)// launcher
            finish()     // Kill Splash Screen
        },4000)

    }
}