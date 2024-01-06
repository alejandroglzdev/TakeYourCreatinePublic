package com.alejandroglzdev.takeyourcreatine.ui.component.items

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alejandroglzdev.takeyourcreatine.ui.theme.Secondary
import com.alejandroglzdev.takeyourcreatine.ui.theme.SecondaryDark
import com.alejandroglzdev.takeyourcreatine.ui.theme.labelSmallAccent
import com.alejandroglzdev.takeyourcreatine.ui.theme.labelSmallSecondaryDark
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Calendar(registers: List<LocalDate>, month: LocalDate) {
    // Parameter month has a meaning. In the case that a month doesn't have a register, we must print
    // the calendar also. We had (firstDay = registers.first()), so it was crashing because the list
    // was null.

    val monthYearFormatter = DateTimeFormatter.ofPattern("MMMM y")
    val monthYearStr = month.format(monthYearFormatter)

    Column(
        modifier = Modifier
            .padding(32.dp)
            .background(Secondary),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Text(
            text = monthYearStr.replaceFirstChar(Char::titlecase),
            style = MaterialTheme.typography.labelSmall,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
        )

        val daysInMonth = month.lengthOfMonth()
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

        val firstDayOfMonth = month.withDayOfMonth(1)
        val firstDayOfWeek = firstDayOfMonth.dayOfWeek.value % 7
        var currentDay = 1

        // Render calendar days
        repeat(6) { // Assuming a maximum of 6 rows for simplicity
            Row {
                for (i in 0 until 7) {
                    var background = Secondary
                    var style = labelSmallSecondaryDark

                    registers.forEach { register ->
                        if (register.dayOfMonth == currentDay) {
                            background = SecondaryDark
                            style = labelSmallAccent
                        }
                    }

                    if (currentDay <= daysInMonth && (i >= firstDayOfWeek || currentDay > 1)) {
                        Box(
                            Modifier.weight(1f),
                            contentAlignment = Alignment.Center
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