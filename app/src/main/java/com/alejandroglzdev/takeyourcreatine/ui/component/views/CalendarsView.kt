package com.alejandroglzdev.takeyourcreatine.ui.component.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.alejandroglzdev.takeyourcreatine.countMonths
import com.alejandroglzdev.takeyourcreatine.ui.component.items.AdmobBanner
import com.alejandroglzdev.takeyourcreatine.ui.component.items.Calendar
import com.alejandroglzdev.takeyourcreatine.ui.theme.Primary
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarsView(registers: List<LocalDateTime>) {
    // Take one register per month until today (so you can know how many months you must print)
    // val registersOnePerMonth = registers.distinctBy { it.month }.filter { it <= LocalDate.now() }

    ConstraintLayout(
        Modifier
            .background(Primary)
            .fillMaxSize()
    ) {
        val (column, adBox) = createRefs()
        val registersOnePerMonth = countMonths(registers.first())
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .constrainAs(column) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            registersOnePerMonth.forEach { registersOnePerMonth ->
                //Once we have a list filtered with one register per month we iterate it
                //So now, we filter the list again, just to know the days that belong to the month that we are working with
                val registersMonthly =
                    registers.filter { it.month == registersOnePerMonth.month && it.year == registersOnePerMonth.year }
                Calendar(registers = registersMonthly, registersOnePerMonth.toLocalDate())
            }


        }
        Box(modifier = Modifier.constrainAs(adBox) {
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            AdmobBanner(padding = 12.dp, banner = "ca-app-pub-3940256099942544/6300978111")
        }
    }
}