package com.alejandroglzdev.takeyourcreatine

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
@Composable
fun Calendars(registers: List<LocalDate>) {
    //Take one register per month until today (so you can know how many months you must print)
    val registersOnePerMonth = registers.distinctBy { it.month }.filter { it <= LocalDate.now() }
    Column {
        registersOnePerMonth.forEach { registersOnePerMonth ->
            //Once we have a list filtered with one register per month we iterate it
            //So now, we filter the list again, just to know the days that belong to the month that we are working with
            val registersMonthly =
                registers.filter { it.month == registersOnePerMonth.month && it.year == registersOnePerMonth.year }
            Calendar(registers = registersMonthly)
        }
    }


}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Calendar(registers: List<LocalDate>) {
    val firstDay = registers.first()
    val monthYearFormatter = DateTimeFormatter.ofPattern("MMMM YYYY")
    val monthYearStr = firstDay.format(monthYearFormatter)

    Column(
        modifier = Modifier.padding(16.dp).background(Secondary),
        horizontalAlignment = CenterHorizontally,

    ) {
        Text(
            text = monthYearStr.replaceFirstChar(Char::titlecase),
            style = MaterialTheme.typography.labelSmall,
            color = Color.Black,
            modifier = Modifier.align(CenterHorizontally).padding(8.dp)
        )

        val daysInMonth = firstDay.lengthOfMonth()
        val daysOfWeek = listOf("S", "M", "T", "W", "T", "F", "S")

        // Render days of the week headers
        Row {
            for (day in daysOfWeek) {
                Text(
                    text = day,
                    style = labelSmallAccent,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                )
            }
        }

        val firstDayOfMonth = firstDay.withDayOfMonth(1)
        val firstDayOfWeek = firstDayOfMonth.dayOfWeek.value % 7
        var currentDay = 1

        // Render calendar days
        repeat(6) { // Assuming a maximum of 6 rows for simplicity
            Row {
                for (i in 0 until 7) {
                    var border = BorderStroke(1.dp, Secondary)
                    var background = Secondary
                    var style = labelSmallSecondaryDark

                    registers.forEach { register ->
                        if (register.dayOfMonth == currentDay){
                            border = BorderStroke(1.dp, SecondaryDark)
                            background = SecondaryDark
                            style = labelSmallAccent
                        }
                    }

                    if (currentDay <= daysInMonth && (i >= firstDayOfWeek || currentDay > 1)) {
                        Box(
                            Modifier.weight(1f),
                            contentAlignment = Center
                        ) {
                            Text(
                                text = currentDay.toString(),
                                style = style,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .clip(CircleShape)
                                    .size(25.dp)
                                    .background(background)
                            )
                            currentDay++
                        }

                    } else {
                        Text(
                            text = "",
                            modifier = Modifier
                                .weight(1f)
                                .padding(4.dp)
                        )
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TakeYourCreatineTheme {
        Greeting("Android")
    }
}