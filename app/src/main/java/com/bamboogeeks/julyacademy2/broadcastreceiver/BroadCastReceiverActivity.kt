package com.bamboogeeks.julyacademy2.broadcastreceiver

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bamboogeeks.julyacademy2.R

class BroadCastReceiverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broad_cast_recveiver)

        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {intentFilter ->
            registerReceiver(AirPlaneModeChangedReceiver(),intentFilter)
        }

    }
}