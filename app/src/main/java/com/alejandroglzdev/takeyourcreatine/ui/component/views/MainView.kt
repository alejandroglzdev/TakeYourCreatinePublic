package com.alejandroglzdev.takeyourcreatine.ui.component.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alejandroglzdev.takeyourcreatine.ui.component.items.BottomAppBar
import com.alejandroglzdev.takeyourcreatine.ui.component.items.TopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView() {
        Scaffold(topBar = { TopAppBar() },
            bottomBar = { BottomAppBar() },
        ) { paddingValues ->
           Box(modifier = Modifier.padding(paddingValues)) {
               HomeView()
               //SettingsView()
           }

        }
    }