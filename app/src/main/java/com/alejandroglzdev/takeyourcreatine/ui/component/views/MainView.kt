package com.alejandroglzdev.takeyourcreatine.ui.component.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alejandroglzdev.takeyourcreatine.navigation.Screens
import com.alejandroglzdev.takeyourcreatine.ui.component.items.BottomAppBar
import com.alejandroglzdev.takeyourcreatine.ui.component.items.TopAppBar
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        topBar = { TopAppBar() },
        bottomBar = {
            BottomAppBar(
                currentDestination = currentDestination,
                navController = navController
            )
        },
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            NavHost(
                navController = navController,
                startDestination = Screens.HomeView.name
            ) {
                composable(route = Screens.HomeView.name) {
                    HomeView()
                }
                composable(route = Screens.CalendarView.name) {
                    val fechasMes1: List<LocalDate> = listOf(
                        LocalDate.of(2023, 10, 1),
                        LocalDate.of(2023, 11, 1),
                        LocalDate.of(2023, 11, 2),
                        LocalDate.of(2024, 1, 1),
                        LocalDate.of(2024, 1, 15),
                        LocalDate.of(2024, 2, 5),
                        LocalDate.of(2024, 3, 10),
                        LocalDate.of(2024, 4, 20),
                        LocalDate.of(2024, 5, 30),
                        LocalDate.of(2024, 6, 25),
                        LocalDate.of(2024, 7, 12),
                        LocalDate.of(2024, 8, 8),
                        LocalDate.of(2024, 9, 17),
                        LocalDate.of(2024, 10, 5),
                        LocalDate.of(2024, 11, 20),
                        LocalDate.of(2024, 12, 29),
                        LocalDate.of(2025, 1, 5)
                    )

                    CalendarsView(fechasMes1)
                }
                composable(route = Screens.SettingsView.name) {
                    SettingsView()
                }
            }
        }

    }
}