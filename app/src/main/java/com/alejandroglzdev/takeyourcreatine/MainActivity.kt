package com.alejandroglzdev.takeyourcreatine

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alejandroglzdev.takeyourcreatine.ui.component.items.Calendar
import com.alejandroglzdev.takeyourcreatine.ui.component.views.Calendars
import com.alejandroglzdev.takeyourcreatine.ui.theme.Secondary
import com.alejandroglzdev.takeyourcreatine.ui.theme.SecondaryDark
import com.alejandroglzdev.takeyourcreatine.ui.theme.TakeYourCreatineTheme
import com.alejandroglzdev.takeyourcreatine.ui.theme.labelSmallSecondaryDark
import com.alejandroglzdev.takeyourcreatine.ui.theme.labelSmallAccent
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TakeYourCreatineTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val fechasMes1: List<LocalDate> = listOf(
        LocalDate.of(2023, 10, 1),
        LocalDate.of(2023, 11, 1),
        LocalDate.of(2023, 11, 2),
        LocalDate.of(2024, 1, 1),
        LocalDate.of(2024, 1, 15),
        LocalDate.of(2024, 2, 5),
        LocalDate.of(2024, 3, 10),
        LocalDate.of(2024, 4, 20),
        LocalDate.of(2024, 5, 30),
        LocalDate.of(2024, 6, 25),
        LocalDate.of(2024, 7, 12),
        LocalDate.of(2024, 8, 8),
        LocalDate.of(2024, 9, 17),
        LocalDate.of(2024, 10, 5),
        LocalDate.of(2024, 11, 20),
        LocalDate.of(2024, 12, 29),
        LocalDate.of(2025, 1, 5)
    )

    Calendars(fechasMes1)
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

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TakeYourCreatineTheme {
        Greeting("Android")
    }
}