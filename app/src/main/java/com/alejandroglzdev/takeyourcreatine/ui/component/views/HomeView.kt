package com.alejandroglzdev.takeyourcreatine.ui.component.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.alejandroglzdev.takeyourcreatine.CreatineViewModel
import com.alejandroglzdev.takeyourcreatine.R
import com.alejandroglzdev.takeyourcreatine.data.database.entities.UserRegisters
import com.alejandroglzdev.takeyourcreatine.navigation.BottomNavItem
import com.alejandroglzdev.takeyourcreatine.ui.component.items.AdmobBanner
import com.alejandroglzdev.takeyourcreatine.ui.component.items.SquareButton
import com.alejandroglzdev.takeyourcreatine.ui.theme.Primary
import com.alejandroglzdev.takeyourcreatine.ui.theme.headlineSmallAccent
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeView(creatineViewModel: CreatineViewModel, navController: NavHostController) {
    //Creating MutableStates
    var doneToday by remember { mutableStateOf(false) }
    var isEnabled by remember { mutableStateOf(true) }
    var intakeText by remember { mutableStateOf("Your intake for today is 7 grams") }
    var scoopImage by remember { mutableIntStateOf(R.drawable.scoop_triste) }
    var contentDescription by remember { mutableStateOf("Scoop triste") }

    //Get data from DB
    val userData by creatineViewModel.userData.observeAsState()
    val userRegisters by creatineViewModel.userRegisters.observeAsState()
    val daysRow = userRegisters?.let { creatineViewModel.consecutiveDays(it) } ?: 1

    //Create todays date for insert
    val today = UserRegisters(LocalDateTime.now())

    //Checking if creatine intake is not null
    val creatineIntake = userData.let {
        it?.creatineIntake ?: 1
    }

    //Checking if the current day is the same as the last saved on BD
    doneToday = if (userRegisters?.isNotEmpty() == true) {
        creatineViewModel.localDateTimeIsToday(userRegisters?.last()?.register)

    } else {
        false
    }

    //Intake text
    intakeText = if (doneToday) {
        stringResource(
            R.string.congratulation_you_have_been_taking_creatine_for_days_in_a_row,
            daysRow
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

            if (!doneToday) {
                SquareButton(
                    onClick = {
                        creatineViewModel.insertUserRegisters(today)

                        navController.popBackStack()
                        navController.navigate(BottomNavItem.MainView.route)


                    },
                    content = stringResource(R.string.take_your_creatine),
                    enabled = isEnabled
                )
            }
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