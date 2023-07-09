package com.bamboogeeks.julyacademy2.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bamboogeeks.julyacademy2.calculatorapp.CalculatorActivity
import com.bamboogeeks.julyacademy2.databinding.ActivityLoginScreenBinding
import com.bamboogeeks.julyacademy2.resturantapp.RestaurantActivity
import com.bamboogeeks.julyacademy2.todolistapp.ui.TodolistActivity

class LoginScreen : AppCompatActivity() {
    private lateinit var binding: ActivityLoginScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, TodolistActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, RegistrationScreen::class.java)
            startActivity(intent)
            finish()
        }
    }
}