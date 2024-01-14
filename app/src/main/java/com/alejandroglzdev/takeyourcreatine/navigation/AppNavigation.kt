package com.alejandroglzdev.takeyourcreatine.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alejandroglzdev.takeyourcreatine.CreatineViewModel
import com.alejandroglzdev.takeyourcreatine.ui.component.views.BodyweightView
import com.alejandroglzdev.takeyourcreatine.ui.component.views.CalendarsView
import com.alejandroglzdev.takeyourcreatine.ui.component.views.HomeView
import com.alejandroglzdev.takeyourcreatine.ui.component.views.MainView
import com.alejandroglzdev.takeyourcreatine.ui.component.views.SettingsView
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(navController: NavHostController, creatineViewModel: CreatineViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screens.BodyweightView.name
    ) {
        composable(route = Screens.HomeView.name) {
            MainView(navController = navController) { HomeView() }
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

            MainView(navController = navController) { CalendarsView(fechasMes1) }
        }
        composable(route = Screens.SettingsView.name) {
            MainView(navController = navController) { SettingsView() }
        }
        composable(route = Screens.BodyweightView.name) {
            BodyweightView(navController = navController, creatineViewModel = creatineViewModel)
        }
        composable(route = Screens.MainView.name) {
            MainView(navController = navController) { HomeView() }
        }
    }

}
