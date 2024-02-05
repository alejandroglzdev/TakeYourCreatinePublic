package com.alejandroglzdev.takeyourcreatine.ui.component.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.alejandroglzdev.takeyourcreatine.CreatineViewModel
import com.alejandroglzdev.takeyourcreatine.R
import com.alejandroglzdev.takeyourcreatine.data.database.entities.UserData
import com.alejandroglzdev.takeyourcreatine.navigation.BottomNavItem
import com.alejandroglzdev.takeyourcreatine.ui.component.items.AdmobBanner
import com.alejandroglzdev.takeyourcreatine.ui.component.items.SquareButton
import com.alejandroglzdev.takeyourcreatine.ui.theme.Accent
import com.alejandroglzdev.takeyourcreatine.ui.theme.Primary
import com.alejandroglzdev.takeyourcreatine.ui.theme.PrimaryDark
import com.alejandroglzdev.takeyourcreatine.ui.theme.SecondaryDark
import com.alejandroglzdev.takeyourcreatine.ui.theme.headlineMediumAccent
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SettingsView(
    navController: NavHostController,
    userData: UserData?,
    creatineViewModel: CreatineViewModel
) {
    val switchState = userData?.notifications ?: false
    val checkedState = remember { mutableStateOf(switchState) }

    if(checkedState.value) creatineViewModel.ShowNotificationsDialogue()

    val colors = if (checkedState.value) {
        ButtonDefaults.buttonColors(
            containerColor = SecondaryDark,
            contentColor = Accent,
            disabledContainerColor = SecondaryDark,
            disabledContentColor = Accent
        )
    } else {
        ButtonDefaults.buttonColors(
            containerColor = SecondaryDark.copy(alpha = 0.5f),
            contentColor = Accent.copy(alpha = 0.5f),
            disabledContainerColor = SecondaryDark.copy(alpha = 0.5f),
            disabledContentColor = Accent.copy(alpha = 0.5f)
        )

    }

    var pickedTime by remember { mutableStateOf(userData?.hour ?: LocalTime.NOON) }
    val formattedTime by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("HH:mm")
                .format(pickedTime)
        }
    }
    val timeDialogState = rememberMaterialDialogState()


    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .background(Primary),

        ) {
        val (box, adBox) = createRefs()

        Box(modifier = Modifier
            .fillMaxHeight(0.55f)
            .fillMaxWidth(0.8f)
            .background(PrimaryDark)
            .padding(32.dp)
            .constrainAs(box) {
                top.linkTo(parent.top, margin = 24.dp)
                bottom.linkTo(adBox.top, margin = 24.dp)
                start.linkTo(parent.start, margin = 24.dp)
                end.linkTo(parent.end, margin = 24.dp)
            }) {
            Column {
                Text(
                    text = stringResource(R.string.settings),
                    style = headlineMediumAccent,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxHeight(0.4f)
                )

                SquareButton(
                    onClick = {
                        navController.popBackStack()
                        navController.navigate(BottomNavItem.Bodyweight.route)
                    },
                    content = stringResource(R.string.change_creatine_intake),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp),
                    textAlign = TextAlign.Start

                )

                Row(
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .background(SecondaryDark)
                        .fillMaxWidth()
                        .height(40.dp),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Text(
                        text = "Notifications",
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier
                            .weight(0.7f)
                            .padding(start = 12.dp),
                        textAlign = TextAlign.Start
                    )

                    Switch(
                        checked = checkedState.value,
                        onCheckedChange = {
                            checkedState.value = it
                            val newUserData = UserData(
                                notifications = it,
                                onboard = userData?.onboard,
                                creatineIntake = userData?.creatineIntake,
                                hour = userData?.hour,
                                id = userData?.id
                            )
                            creatineViewModel.updateUserDataAndReloadData(newUserData)
                        },
                        modifier = Modifier
                            .weight(0.3f)
                            .scale(0.6f),
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Accent,
                            checkedTrackColor = SecondaryDark,
                            checkedBorderColor = Accent
                        )

                    )

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SquareButton(
                        onClick = { timeDialogState.show() },
                        content = stringResource(R.string.select_hour),
                        textAlign = TextAlign.Start,
                        enabled = switchState,
                        colors = colors,
                        modifier = Modifier
                            .weight(0.7f)
                            .padding(end = 8.dp)
                    )

                    SquareButton(
                        onClick = { timeDialogState.show() },
                        content = formattedTime,
                        textAlign = TextAlign.Start,
                        enabled = switchState,
                        colors = colors,
                        modifier = Modifier
                            .weight(0.3f)
                            .padding(start = 8.dp)
                    )

                }

                MaterialDialog(
                    dialogState = timeDialogState,
                    buttons = {
                        positiveButton(text = stringResource(R.string.ok)) {
                            val newUserData = UserData(
                                notifications = userData?.notifications,
                                onboard = userData?.onboard,
                                creatineIntake = userData?.creatineIntake,
                                hour = pickedTime,
                                id = userData?.id
                            )
                            creatineViewModel.updateUserDataAndReloadData(newUserData)
                        }
                        negativeButton(text = stringResource(R.string.cancel))
                    },
                ) {
                    timepicker(
                        initialTime = LocalTime.NOON,
                        title = stringResource(R.string.pick_a_time),
                        timeRange = LocalTime.MIN..LocalTime.MAX
                    ) {
                        pickedTime = it
                    }
                }

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