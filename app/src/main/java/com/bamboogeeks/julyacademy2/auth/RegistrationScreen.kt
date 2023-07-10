package com.bamboogeeks.julyacademy2.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bamboogeeks.julyacademy2.calculatorapp.CalculatorActivity
import com.bamboogeeks.julyacademy2.databinding.ActivityRegistrationScreenBinding
import com.bamboogeeks.julyacademy2.ui.MainActivity

class RegistrationScreen : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, LoginScreen::class.java)
            startActivity(intent)
            finish()
        }
    }
}