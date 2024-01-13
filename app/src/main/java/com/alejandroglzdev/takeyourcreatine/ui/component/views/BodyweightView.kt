package com.alejandroglzdev.takeyourcreatine.ui.component.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
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
import com.alejandroglzdev.takeyourcreatine.ui.theme.Secondary
import com.alejandroglzdev.takeyourcreatine.ui.theme.headlineMediumAccent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodyweightView(welcome: Boolean = false, navController: NavHostController) {
    var text by rememberSaveable { mutableStateOf("") }

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
                if (welcome) {
                    Text(
                        text = stringResource(R.string.welcome_let_s_get_started),
                        style = headlineMediumAccent,
                        modifier = Modifier.fillMaxHeight(0.55f)
                    )

                } else {
                    Text(
                        text = stringResource(R.string.change_your_creatine_intake),
                        style = headlineMediumAccent,
                        modifier = Modifier.fillMaxHeight(0.55f)
                    )
                }

                //Controlar input
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Enter your bodyweight in KG") },
                    shape = RoundedCornerShape(32.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Accent,
                        containerColor = Secondary,
                        focusedBorderColor = Accent,
                        focusedLabelColor = Accent,
                        unfocusedLabelColor = Accent,
                        cursorColor = Accent,
                        //Puede estar guay -> selectionColors = TextSelectionColors(Color.Red, Color.Red)
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                SquareButton(onClick = {
                    navController.popBackStack()
                    navController.navigate(BottomNavItem.MainView.route)


                }, content = stringResource(R.string.submit))
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