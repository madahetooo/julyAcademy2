package com.bamboogeeks.julyacademy2.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.bamboogeeks.julyacademy2.calculatorapp.CalculatorActivity
import com.bamboogeeks.julyacademy2.databinding.ActivityLoginScreenBinding
import com.bamboogeeks.julyacademy2.resturantapp.RestaurantActivity
import com.bamboogeeks.julyacademy2.todolistapp.ui.TodolistActivity
import com.bamboogeeks.julyacademy2.ui.MainActivity

class LoginScreen : AppCompatActivity() {
    private lateinit var binding: ActivityLoginScreenBinding
    var pressTwiceToExit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, RegistrationScreen::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        if (pressTwiceToExit == true){
            finish() // Close App
            super.onBackPressed()
        }
        pressTwiceToExit = true
        Toast.makeText(this,"Press Again Quickly to Exit",Toast.LENGTH_SHORT).show()
        Handler().postDelayed({
            pressTwiceToExit = false
        },3000)

    }
}