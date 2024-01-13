package com.alejandroglzdev.takeyourcreatine

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.alejandroglzdev.takeyourcreatine.navigation.AppNavigation
import com.alejandroglzdev.takeyourcreatine.ui.theme.TakeYourCreatineTheme
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TakeYourCreatineTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(navController)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun countMonths(register: LocalDate): List<LocalDate> {
    val datePerMonth = mutableListOf<LocalDate>()
    var todayDate = register

    // It looks if the date is before today, because if it's we must add a month

    // If the date is Today, because if it's we must add a month also

    // If the date is in the same month and year, because if it's we must add a month
    // (Example: The first date is 20/11/23 and today is 06/01/24)
    // so it will start adding months. There will be a case, where the date that we were adding up one month
    // will be 20/01/24. So it won't print January's calendar. With this last condition, it'll check that.

    while (todayDate.isBefore(LocalDate.now()) || todayDate.isEqual(LocalDate.now()) || (todayDate.month == LocalDate.now().month && todayDate.year == LocalDate.now().year)) {
        datePerMonth.add(todayDate)
        todayDate = todayDate.plusMonths(1)
    }

    return datePerMonth
}