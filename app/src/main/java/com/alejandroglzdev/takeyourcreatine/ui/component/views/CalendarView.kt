package com.alejandroglzdev.takeyourcreatine.ui.component.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alejandroglzdev.takeyourcreatine.countMonths
import com.alejandroglzdev.takeyourcreatine.ui.component.items.AdmobBanner
import com.alejandroglzdev.takeyourcreatine.ui.component.items.Calendar
import com.alejandroglzdev.takeyourcreatine.ui.theme.Primary
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Calendars(registers: List<LocalDate>) {
    // Take one register per month until today (so you can know how many months you must print)
    // val registersOnePerMonth = registers.distinctBy { it.month }.filter { it <= LocalDate.now() }

    val registersOnePerMonth = countMonths(registers.first())
    Column(
        Modifier
            .background(Primary)
            .verticalScroll(rememberScrollState())
    ) {
        registersOnePerMonth.forEach { registersOnePerMonth ->
            //Once we have a list filtered with one register per month we iterate it
            //So now, we filter the list again, just to know the days that belong to the month that we are working with
            val registersMonthly =
                registers.filter { it.month == registersOnePerMonth.month && it.year == registersOnePerMonth.year }
            Calendar(registers = registersMonthly, registersOnePerMonth)
        }
        
        AdmobBanner(padding = 12.dp,banner = "ca-app-pub-3940256099942544/6300978111")
    }


}