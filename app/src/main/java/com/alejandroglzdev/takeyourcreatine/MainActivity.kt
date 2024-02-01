package com.alejandroglzdev.takeyourcreatine

import android.Manifest
import android.content.pm.PackageManager
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
import androidx.navigation.compose.rememberNavController
import com.alejandroglzdev.takeyourcreatine.MainActivity.Companion.MY_CHANNEL_ID
import com.alejandroglzdev.takeyourcreatine.navigation.AppNavigation
import com.alejandroglzdev.takeyourcreatine.ui.theme.TakeYourCreatineTheme
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.LocalDateTime

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val creatineViewModel: CreatineViewModel by viewModels()
    private val permissionsToRequest = arrayOf(
        Manifest.permission.POST_NOTIFICATIONS
    )

    companion object {
        const val MY_CHANNEL_ID = "myChannel"
    }

    @RequiresApi(Build.VERSION_CODES.O)
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
                    AppNavigation(navController, creatineViewModel)
                }
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

@Composable
fun createSimpleNotification() {
    var builder = NotificationCompat.Builder(LocalContext.current, MY_CHANNEL_ID)
        .setSmallIcon(android.R.drawable.ic_menu_close_clear_cancel)
        .setContentTitle("MyNotification")
        .setContentText("MyBody")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    with(NotificationManagerCompat.from(LocalContext.current)) {
        if (ActivityCompat.checkSelfPermission(
                LocalContext.current,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //ActivityCompat.requestPermissions()
            return
        }
        notify(1, builder.build())
    }
}