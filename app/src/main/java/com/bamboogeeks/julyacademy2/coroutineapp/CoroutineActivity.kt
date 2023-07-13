package com.bamboogeeks.julyacademy2.coroutineapp

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bamboogeeks.julyacademy2.R
import com.bamboogeeks.julyacademy2.databinding.ActivityCoroutineBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
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

        GlobalScope.launch(Dispatchers.IO) {

            val result1 = async { doNetworkCall1() }
            val result2 = async { doNetworkCall2() }
            Log.d(TAG,"Result 1 is : ${result1.await()}")
            Log.d(TAG,"Result 1 is : ${result2.await()}")


//            var result1:String? =null
//            var result2:String? =null
//
//            launch(Dispatchers.IO) {
//                result1 =  doNetworkCall1()
//            }
//
//            launch(Dispatchers.IO) {
//                result2 =  doNetworkCall2()
//            }
//
//            Log.d(TAG,"Result 1 is equal to : ${result1}")
//            Log.d(TAG,"Result 2 is equal to : ${result2}")

        }

    }
    suspend fun doNetworkCall1():String{
        delay(4000L)
        return "Network Call Result 1"
    }
    suspend fun doNetworkCall2():String{
        delay(4000L)
        return "Network Call Result 2"
    }
}