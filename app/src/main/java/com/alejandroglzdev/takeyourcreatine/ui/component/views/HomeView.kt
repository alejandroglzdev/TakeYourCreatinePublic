package com.alejandroglzdev.takeyourcreatine.ui.component.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.alejandroglzdev.takeyourcreatine.R
import com.alejandroglzdev.takeyourcreatine.ui.component.items.AdmobBanner
import com.alejandroglzdev.takeyourcreatine.ui.component.items.SquareButton
import com.alejandroglzdev.takeyourcreatine.ui.theme.Primary
import com.alejandroglzdev.takeyourcreatine.ui.theme.headlineSmallAccent

@Composable
fun HomeView() {
    ConstraintLayout(Modifier.fillMaxSize().background(Primary),

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

        Box(modifier = Modifier.constrainAs(adBox) {
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            AdmobBanner(padding = 50.dp, banner = "ca-app-pub-3940256099942544/6300978111")
        }
    }


}

@Preview
@Composable
fun Preview() {
    HomeView()
}