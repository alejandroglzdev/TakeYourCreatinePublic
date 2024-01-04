package com.alejandroglzdev.takeyourcreatine

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alejandroglzdev.takeyourcreatine.ui.theme.TakeYourCreatineTheme
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
        LocalDate.of(2024, 1, 1),
        LocalDate.of(2024, 1, 7),
        LocalDate.of(2024, 1, 12),
        LocalDate.of(2024, 1, 18),
        LocalDate.of(2024, 1, 23),
        LocalDate.of(2024, 1, 31)
    )

    Calendar(date = LocalDate.now(), fechasMes1)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Calendar(date: LocalDate, registers: List<LocalDate>) {

    val monthYearFormatter = DateTimeFormatter.ofPattern("MMMM YYYY")
    val monthYearStr = date.format(monthYearFormatter)

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally // Centra el contenido horizontalmente

    ) {
        Text(
            text = monthYearStr.replaceFirstChar(Char::titlecase),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            color = Color.Black,
            modifier = Modifier.align(CenterHorizontally)
        )

        val daysInMonth = date.lengthOfMonth()
        val daysOfWeek = listOf("S", "M", "T", "W", "T", "F", "S")

        // Render days of the week headers
        Row {
            for (day in daysOfWeek) {
                Text(
                    text = day,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                )
            }
        }

        val firstDayOfMonth = date.withDayOfMonth(1)
        val firstDayOfWeek = firstDayOfMonth.dayOfWeek.value % 7
        var currentDay = 1

        // Render calendar days
        repeat(6) { // Assuming a maximum of 6 rows for simplicity
            Row {
                for (i in 0 until 7) {
                    var border = BorderStroke(1.dp, Color.White)

                    registers.forEach {register ->
                        if (register.dayOfMonth == currentDay) border = BorderStroke(1.dp, Color.Black)
                    }

                    if (currentDay <= daysInMonth && (i >= firstDayOfWeek || currentDay > 1)) {
                        Box(
                            Modifier.weight(1f),
                            contentAlignment = Center
                        ) {
                            Text(
                                text = currentDay.toString(),
                                style = TextStyle(fontSize = 16.sp),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .border(border, CircleShape)
                                    .size(25.dp)
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