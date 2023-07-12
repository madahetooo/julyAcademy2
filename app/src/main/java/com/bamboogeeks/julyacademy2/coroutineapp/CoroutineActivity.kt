package com.bamboogeeks.julyacademy2.coroutineapp

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bamboogeeks.julyacademy2.R
import com.bamboogeeks.julyacademy2.databinding.ActivityCoroutineBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutineActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoroutineBinding
    val TAG = "CoroutineActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG,"This is Thread ${Thread.currentThread().name}")

        CoroutineScope(Dispatchers.IO).launch {
            delay(5000L)
            Log.d(TAG,"This is  Thread ${Thread.currentThread().name}")

            withContext(Dispatchers.Main){
                binding.txDummyText.text = "This is New Dummy Text"
            }

        }

    }
}