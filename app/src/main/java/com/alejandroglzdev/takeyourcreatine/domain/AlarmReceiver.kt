package com.alejandroglzdev.takeyourcreatine.domain

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.alejandroglzdev.takeyourcreatine.MainActivity

class AlarmReceiver(): BroadcastReceiver(){
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onReceive(context: Context, intent: Intent) {
        val notificationManager = ContextCompat.getSystemService(
            context,
            NotificationManager::class.java
        ) as NotificationManager

        notificationManager.sendReminderNotification(
            applicationContext = context,
            channelId = "myChannel"
        )
        // Remove this line if you don't want to reschedule the reminder
        val reminderManager = ReminderManager(context)
        reminderManager.startReminder()
    }
}

fun NotificationManager.sendReminderNotification(
    applicationContext: Context,
    channelId: String,
) {
    val contentIntent = Intent(applicationContext, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    val pendingIntent = PendingIntent.getActivity(
        applicationContext,
        1,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )
    val builder = NotificationCompat.Builder(applicationContext, channelId)
        .setContentTitle("MyNotification")
        .setContentText("MyBody")
        .setSmallIcon(android.R.drawable.ic_menu_close_clear_cancel)
        .setStyle(
            NotificationCompat.BigTextStyle()
                .bigText("Content Text largo...\nContent Text largo...\nContent Text largo...\nContent Text largo...\n")
        )
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)

    notify(NOTIFICATION_ID, builder.build())
}

const val NOTIFICATION_ID = 1