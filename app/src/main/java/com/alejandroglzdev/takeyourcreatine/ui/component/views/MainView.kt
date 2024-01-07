package com.alejandroglzdev.takeyourcreatine.ui.component.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import com.alejandroglzdev.takeyourcreatine.ui.component.items.BottomAppBar
import com.alejandroglzdev.takeyourcreatine.ui.component.items.TopAppBar
import com.alejandroglzdev.takeyourcreatine.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView() {
    Scaffold(topBar = { TopAppBar() },
        bottomBar = { BottomAppBar() }
    ) { _ ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Primary),
            contentAlignment = Center
        ) {
            HomeView()
        }

    }
}