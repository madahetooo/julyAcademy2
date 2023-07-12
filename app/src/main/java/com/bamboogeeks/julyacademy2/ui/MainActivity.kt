package com.bamboogeeks.julyacademy2.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bamboogeeks.julyacademy2.R
import com.bamboogeeks.julyacademy2.broadcastreceiver.BroadCastReceiverActivity
import com.bamboogeeks.julyacademy2.calculatorapp.CalculatorActivity
import com.bamboogeeks.julyacademy2.coroutineapp.CoroutineActivity
import com.bamboogeeks.julyacademy2.databinding.ActivityMainBinding
import com.bamboogeeks.julyacademy2.navigationdrawerapp.NavigationDrawerAppActivity
import com.bamboogeeks.julyacademy2.notificationapp.NotificationActivity
import com.bamboogeeks.julyacademy2.resturantapp.RestaurantActivity
import com.bamboogeeks.julyacademy2.todolistapp.ui.TodolistActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCalculatorApp.setOnClickListener {
            val intent = Intent(this, CalculatorActivity::class.java)
            startActivity(intent)
        }
        binding.btnRestaurantApp.setOnClickListener {
            val intent = Intent(this, RestaurantActivity::class.java)
            startActivity(intent)

        }
        binding.btnTodolistApp.setOnClickListener {
            val intent = Intent(this, TodolistActivity::class.java)
            startActivity(intent)
        }
        binding.btnNavigationDrawerApp.setOnClickListener {
            val intent = Intent(this, NavigationDrawerAppActivity::class.java)
            startActivity(intent)
        }
        binding.btnNotificationApp.setOnClickListener {
            val intent = Intent(this, NotificationActivity::class.java)
            startActivity(intent)
        }
        binding.btnBroadCastReceiver.setOnClickListener {
            val intent = Intent(this, BroadCastReceiverActivity::class.java)
            startActivity(intent)
        }
        binding.btnKotlinCoroutineApp.setOnClickListener {
            val intent = Intent(this, CoroutineActivity::class.java)
            startActivity(intent)
        }
    }
}