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

class AlarmReceiver() : BroadcastReceiver() {
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
        .setContentTitle("It's time to maximize your workout!")
        .setContentText("Don't forget to take your creatine! Keep pushing!")
        .setSmallIcon(android.R.drawable.ic_menu_close_clear_cancel)
        .setStyle(
            NotificationCompat.BigTextStyle()
                .bigText("Don't forget to take your daily dose of creatine to boost your performance and reach your fitness goals. Remember, each dose brings you closer to smashing your personal records. Don't miss out on this opportunity to push your limits and dominate your workouts!")
        )
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)

    notify(NOTIFICATION_ID, builder.build())
}

const val NOTIFICATION_ID = 1