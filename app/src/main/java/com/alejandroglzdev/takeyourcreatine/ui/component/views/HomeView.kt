package com.alejandroglzdev.takeyourcreatine.ui.component.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.alejandroglzdev.takeyourcreatine.CreatineViewModel
import com.alejandroglzdev.takeyourcreatine.R
import com.alejandroglzdev.takeyourcreatine.ui.component.items.AdmobBanner
import com.alejandroglzdev.takeyourcreatine.ui.component.items.SquareButton
import com.alejandroglzdev.takeyourcreatine.ui.theme.Primary
import com.alejandroglzdev.takeyourcreatine.ui.theme.headlineSmallAccent
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeView(creatineViewModel: CreatineViewModel) {
    //Get data from DB
    creatineViewModel.getUserData()
    creatineViewModel.getUserRegisters()
    val userData by creatineViewModel.userData.observeAsState()
    val userRegisters by creatineViewModel.userRegisters.observeAsState()

    //Checking if creatine intake is not null
    val creatineIntake = userData.let {
        it?.creatineIntake ?: 1
    }

    //Checking if the current day is the same as the last saved on BD
    val doneToday =
        !(userRegisters?.isEmpty() == true || userRegisters?.last()?.register != LocalDateTime.now())

    //Creating MutableStates
    var isEnabled by remember { mutableStateOf(true) }
    var intakeText by remember { mutableStateOf("Your intake for today is 7 grams") }
    var scoopImage by remember { mutableStateOf(R.drawable.scoop_triste) }
    var contentDescription by remember { mutableStateOf("Scoop triste") }

    //Intake text
    intakeText = if (doneToday) {
        stringResource(
            R.string.congratulation_you_have_been_taking_creatine_for_days_in_a_row,
            //daysRow
            2
        )
    } else {
        stringResource(
            R.string.your_intake_for_today_is_grams,
            creatineIntake
        )
    }

    //Image
    scoopImage = if (doneToday) {
        R.drawable.scoop_feliz

    } else {
        R.drawable.scoop_triste

    }

    //Content description
    contentDescription = if (doneToday) {
        stringResource(R.string.happy_scoop)
    } else {
        stringResource(R.string.sad_scoop)
    }

    //Button
    if (doneToday) isEnabled = false

    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .background(Primary),

        ) {
        val (box, adBox) = createRefs()
        Column(
            modifier = Modifier
                .background(Primary)
                .padding(32.dp)
                .constrainAs(box) {
                    top.linkTo(parent.top)
                    bottom.linkTo(adBox.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Text(
                text = intakeText,
                style = headlineSmallAccent,
                textAlign = TextAlign.Center
            )

            Image(
                painterResource(id = scoopImage),
                contentDescription = contentDescription
            )

            SquareButton(
                onClick = { /*TODO*/ },
                content = stringResource(R.string.take_your_creatine),
                enabled = isEnabled
            )
        }

        Box(modifier = Modifier.constrainAs(adBox) {
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            AdmobBanner(padding = 50.dp, banner = "ca-app-pub-3940256099942544/6300978111")
        }
    }


}