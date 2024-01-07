package com.alejandroglzdev.takeyourcreatine.ui.component.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alejandroglzdev.takeyourcreatine.R
import com.alejandroglzdev.takeyourcreatine.ui.component.items.SquareButton
import com.alejandroglzdev.takeyourcreatine.ui.theme.Primary
import com.alejandroglzdev.takeyourcreatine.ui.theme.headlineSmallAccent

@Composable
fun HomeView() {
    Column(
        modifier = Modifier
            .background(Primary)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.your_intake_for_today_is_7_grams),
            style = headlineSmallAccent,
            textAlign = TextAlign.Center
        )

        Image(
            painterResource(id = R.drawable.scoop_triste),
            contentDescription = stringResource(R.string.sad_scoop)
        )

        SquareButton(
            onClick = { /*TODO*/ },
            content = "Take your creatine!"
            )

    }
}

@Preview
@Composable
fun Preview() {
    HomeView()
}