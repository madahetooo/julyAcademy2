package com.bamboogeeks.julyacademy2.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirPlaneModeChangedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        var isAirPlaneModeEnabled = intent?.getBooleanExtra("state",false)?:return
        if (isAirPlaneModeEnabled){
            Toast.makeText(context,"AirPlane Mode is Enabled",Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(context,"AirPlane Mode is Disabled",Toast.LENGTH_LONG).show()
        }
    }
}