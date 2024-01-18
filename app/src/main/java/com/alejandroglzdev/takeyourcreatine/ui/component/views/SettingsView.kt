package com.alejandroglzdev.takeyourcreatine.ui.component.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.alejandroglzdev.takeyourcreatine.R
import com.alejandroglzdev.takeyourcreatine.navigation.BottomNavItem
import com.alejandroglzdev.takeyourcreatine.ui.component.items.AdmobBanner
import com.alejandroglzdev.takeyourcreatine.ui.component.items.SquareButton
import com.alejandroglzdev.takeyourcreatine.ui.theme.Accent
import com.alejandroglzdev.takeyourcreatine.ui.theme.Primary
import com.alejandroglzdev.takeyourcreatine.ui.theme.PrimaryDark
import com.alejandroglzdev.takeyourcreatine.ui.theme.SecondaryDark
import com.alejandroglzdev.takeyourcreatine.ui.theme.headlineMediumAccent

@Composable
fun SettingsView(navController: NavHostController) {
    val checkedState = remember { mutableStateOf(true) }

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
                        .background(SecondaryDark)
                        .fillMaxWidth(),
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
                        onCheckedChange = { checkedState.value = it },
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