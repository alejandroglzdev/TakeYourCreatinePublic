package com.alejandroglzdev.takeyourcreatine

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.alejandroglzdev.takeyourcreatine.MainActivity.Companion.MY_CHANNEL_ID
import com.alejandroglzdev.takeyourcreatine.navigation.AppNavigation
import com.alejandroglzdev.takeyourcreatine.ui.theme.TakeYourCreatineTheme
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.LocalDateTime
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.getSystemServiceName
import com.alejandroglzdev.takeyourcreatine.domain.ReminderManager

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val creatineViewModel: CreatineViewModel by viewModels()
    private val permissionsToRequest = arrayOf(
        Manifest.permission.POST_NOTIFICATIONS
    )
    lateinit var context: Context

    companion object {
        const val MY_CHANNEL_ID = "myChannel"
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TakeYourCreatineTheme {
                val navController = rememberNavController()
                //InsertUserDataUseCase(UserData(onboard = true, notifications = true, creatineIntake = 7))
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    context = LocalContext.current
                    val reminderManager = ReminderManager(context)

                    AppNavigation(navController, creatineViewModel, reminderManager)
                    createChannel()
                    createSimpleNotification()
                }
            }
        }

    }
    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                MY_CHANNEL_ID,
                "myChannel",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "myChannel"
            }
            val notificationManager =
                getSystemService(context, NotificationManager::class.java) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun createSimpleNotification() {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent,
            PendingIntent.FLAG_IMMUTABLE)

        var builder = NotificationCompat.Builder(context, MY_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_menu_close_clear_cancel)
            .setContentTitle("MyNotification")
            .setContentText("MyBody")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Content Text largo...\nContent Text largo...\nContent Text largo...\nContent Text largo...\n")
            )
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                notify(1, builder.build())
            }

        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
fun countMonths(register: LocalDateTime): List<LocalDateTime> {
    val datePerMonth = mutableListOf<LocalDateTime>()
    var todayDate = register

    // It looks if the date is before today, because if it's we must add a month

    // If the date is Today, because if it's we must add a month also

    // If the date is in the same month and year, because if it's we must add a month
    // (Example: The first date is 20/11/23 and today is 06/01/24)
    // so it will start adding months. There will be a case, where the date that we were adding up one month
    // will be 20/01/24. So it won't print January's calendar. With this last condition, it'll check that.

    while (todayDate.isBefore(LocalDateTime.now()) || todayDate.isEqual(LocalDateTime.now()) || (todayDate.month == LocalDate.now().month && todayDate.year == LocalDate.now().year)) {
        datePerMonth.add(todayDate)
        todayDate = todayDate.plusMonths(1)
    }

    return datePerMonth
}