package com.alejandroglzdev.takeyourcreatine.ui.component.items

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.alejandroglzdev.takeyourcreatine.R
import com.alejandroglzdev.takeyourcreatine.ui.theme.PrimaryDark
import com.alejandroglzdev.takeyourcreatine.ui.theme.headlineSmallAccent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    CenterAlignedTopAppBar(
        title = { TopAppBarTitle() },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = PrimaryDark
        )
    )
}

@Composable
fun TopAppBarTitle() {
    Text(
        text = stringResource(R.string.takeyourcreatine),
        style = headlineSmallAccent
    )
}

@Preview
@Composable
fun Preview() {
    TopAppBar()
}