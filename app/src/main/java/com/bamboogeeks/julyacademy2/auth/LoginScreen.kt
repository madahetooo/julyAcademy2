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
import com.google.firebase.auth.FirebaseAuth

class LoginScreen : AppCompatActivity() {
    private lateinit var binding: ActivityLoginScreenBinding
    var pressTwiceToExit = false
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()


        val sharedPreferences = getSharedPreferences("loginDataFile",0)
        val editor = sharedPreferences.edit()

        val userNameSharedPreferences = sharedPreferences.getString("userNameKey",null)
        val passwordSharedPreferences = sharedPreferences.getString("passwordKey",null)
        val isCheckedSharedPreferences = sharedPreferences.getBoolean("isCheckedKey",false)

        binding.etEmailAddressLogin.setText(userNameSharedPreferences)
        binding.etPasswordLogin.setText(passwordSharedPreferences)
        binding.chkRememberMe.isChecked = isCheckedSharedPreferences
        Toast.makeText(this,"Data Retrieved Successfully",Toast.LENGTH_LONG).show()


        binding.chkRememberMe.setOnClickListener {
           val userName = binding.etEmailAddressLogin.text.toString()
           val password = binding.etPasswordLogin.text.toString()
           val isChecked = binding.chkRememberMe.isChecked
            editor.apply {
                putString("userNameKey",userName)
                putString("passwordKey",password)
                putBoolean("isCheckedKey",isChecked)
                apply()
            }
            Toast.makeText(this,"Data Saved",Toast.LENGTH_LONG).show()
        }











        binding.btnLogin.setOnClickListener {
            val username = binding.etEmailAddressLogin.text.toString()
            val password = binding.etPasswordLogin.text.toString()
            if (username.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(username, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Authentication Failed", Toast.LENGTH_LONG).show()
                    }
                }
            }


        }
        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, RegistrationScreen::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        if (pressTwiceToExit == true) {
            finish() // Close App
            super.onBackPressed()
        }
        pressTwiceToExit = true
        Toast.makeText(this, "Press Again Quickly to Exit", Toast.LENGTH_SHORT).show()
        Handler().postDelayed({
            pressTwiceToExit = false
        }, 3000)

    }
}