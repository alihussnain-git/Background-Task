package com.justdice.bakgroundtask

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class ActiveTimeWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val sharedPreferences = applicationContext.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        val unlockTime = sharedPreferences.getLong("unlockTime", -1)

        if (unlockTime != -1L) {
            val currentTime = System.currentTimeMillis()
            val activeTime = currentTime - unlockTime

            Log.d("ActiveTimeWorker", "Active time: ${activeTime / 1000} seconds")

            // Show notification with active time
            NotificationHelper.showNotification(applicationContext, activeTime)
        } else {
            Log.d("ActiveTimeWorker", "Unlock time not found")
        }

        return Result.success()
    }
}
