package com.bamboogeeks.julyacademy2.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bamboogeeks.julyacademy2.auth.model.ProfileInformation
import com.bamboogeeks.julyacademy2.calculatorapp.CalculatorActivity
import com.bamboogeeks.julyacademy2.databinding.ActivityRegistrationScreenBinding
import com.bamboogeeks.julyacademy2.ui.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class RegistrationScreen : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationScreenBinding
    private lateinit var auth:FirebaseAuth
    private val profileInformationCollectionRef = Firebase.firestore.collection("ProfileInformation")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        binding.btnRegister.setOnClickListener {
            val emailAddress = binding.etEmailAddress.text.toString()
            val password = binding.etPassword.text.toString()
           val profileInformation = getProfileInformation() // Hold the data
            if (profileInformation != null){
                saveProfileInformation(profileInformation)
            }else{
                Toast.makeText(this@RegistrationScreen, "Please fill the required data",Toast.LENGTH_LONG).show()
            }
            if (emailAddress.isNotEmpty() && password.isNotEmpty()){
                auth.createUserWithEmailAndPassword(emailAddress,password).addOnCompleteListener {task ->
                    if (task.isSuccessful){
                        val user = auth.currentUser
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                }
            }
        }
        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, LoginScreen::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getProfileInformation() : ProfileInformation{
        val fullName = binding.etFullName.text.toString()
        val emailAddress = binding.etEmailAddress.text.toString()
        val phoneNumber = binding.etPhoneNumber.text.toString()
        val password = binding.etPassword.text.toString()
        return ProfileInformation(fullName,emailAddress,phoneNumber,password)
    }

    private fun saveProfileInformation(profileInformation: ProfileInformation){

        CoroutineScope(Dispatchers.IO).launch {
            try {
                profileInformationCollectionRef.add(profileInformation)
                withContext(Dispatchers.Main){
                    Toast.makeText(this@RegistrationScreen, "Data Saved Successfully",Toast.LENGTH_LONG).show()
                }
            }catch (e:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@RegistrationScreen, e.message,Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}