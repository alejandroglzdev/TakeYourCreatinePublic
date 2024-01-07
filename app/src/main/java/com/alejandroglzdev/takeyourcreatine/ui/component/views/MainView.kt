package com.alejandroglzdev.takeyourcreatine.ui.component.views

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.alejandroglzdev.takeyourcreatine.ui.component.items.BottomAppBar
import com.alejandroglzdev.takeyourcreatine.ui.component.items.TopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView() {
    Scaffold(topBar = { TopAppBar() }, bottomBar = { BottomAppBar()}) { padding ->

    }
}

@Preview
@Composable
fun Preview() {
    MainView()
}