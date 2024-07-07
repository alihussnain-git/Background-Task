package com.justdice.bakgroundtask

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class UserPresentReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (Intent.ACTION_USER_PRESENT == intent.action) {
            // User has unlocked the device
            Toast.makeText(context, "Device unlocked", Toast.LENGTH_SHORT).show()

            // Save the current time to SharedPreferences
            val unlockTime = System.currentTimeMillis()
            val sharedPreferences = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
            with(sharedPreferences.edit()) {
                putLong("unlockTime", unlockTime)
                apply()
            }
        }
    }
}
