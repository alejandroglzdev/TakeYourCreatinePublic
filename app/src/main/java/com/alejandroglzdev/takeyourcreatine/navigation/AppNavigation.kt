package com.alejandroglzdev.takeyourcreatine.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alejandroglzdev.takeyourcreatine.CreatineViewModel
import com.alejandroglzdev.takeyourcreatine.ui.component.views.BodyweightView
import com.alejandroglzdev.takeyourcreatine.ui.component.views.CalendarsView
import com.alejandroglzdev.takeyourcreatine.ui.component.views.HomeView
import com.alejandroglzdev.takeyourcreatine.ui.component.views.MainView
import com.alejandroglzdev.takeyourcreatine.ui.component.views.SettingsView

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(navController: NavHostController, creatineViewModel: CreatineViewModel) {
    creatineViewModel.getUserData()
    creatineViewModel.getUserRegisters()
    val userData by creatineViewModel.userData.observeAsState()
    val userRegisters by creatineViewModel.userRegisters.observeAsState()
    val daysRow = userRegisters?.let { creatineViewModel.consecutiveDays(it) } ?: 1
    val onboard = userData == null

    val startDestination = if (onboard) {
        Screens.BodyweightView.name
    } else {
        Screens.MainView.name
    }
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screens.HomeView.name) {
            creatineViewModel.getUserData()
            creatineViewModel.getUserRegisters()
            MainView(navController = navController) {
                HomeView(
                    creatineViewModel = creatineViewModel,
                    navController = navController,
                    userData = userData,
                    userRegisters = userRegisters,
                    daysRow = daysRow
                )
            }
        }
        composable(route = Screens.CalendarView.name) {

            /*
            Warning! The order of the list is important. If it's not ordered
            it won't work.

            val userRegistersList = listOf<UserRegisters>(
                UserRegisters(LocalDateTime.of(2023, 3, 15, 10, 30, 0)),
                UserRegisters(LocalDateTime.of(2023, 8, 15, 10, 30, 0)),
                UserRegisters(LocalDateTime.of(2023, 10, 15, 10, 30, 0)),
                UserRegisters(LocalDateTime.of(2023, 11, 15, 10, 30, 0)),
                UserRegisters(LocalDateTime.of(2023, 12, 15, 10, 30, 0)),
                UserRegisters(LocalDateTime.of(2024, 1, 15, 10, 30, 0))
            )

             */

            val localDateList = if (!(userRegisters.isNullOrEmpty())) {
                creatineViewModel.returnLocalDateList(userRegisters!!)
                //creatineViewModel.returnLocalDateList(userRegistersList)
            } else {
                emptyList()
            }

            MainView(navController = navController) { CalendarsView(localDateList) }
        }
        composable(route = Screens.SettingsView.name) {
            MainView(navController = navController) {
                SettingsView(
                    navController = navController,
                    userData = userData,
                    creatineViewModel = creatineViewModel
                )
            }
        }
        composable(route = Screens.BodyweightView.name) {
            BodyweightView(
                navController = navController,
                creatineViewModel = creatineViewModel,
                welcome = onboard
            )
        }
        composable(route = Screens.MainView.name) {
            creatineViewModel.getUserData()
            creatineViewModel.getUserRegisters()
            MainView(navController = navController) {
                HomeView(
                    creatineViewModel = creatineViewModel,
                    navController = navController,
                    userData = userData,
                    userRegisters = userRegisters,
                    daysRow = daysRow
                )
            }
        }
    }
}