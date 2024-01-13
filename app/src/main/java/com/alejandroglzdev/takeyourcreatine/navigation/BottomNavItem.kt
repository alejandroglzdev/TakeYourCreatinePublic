package com.alejandroglzdev.takeyourcreatine.navigation

import com.alejandroglzdev.takeyourcreatine.R

sealed class BottomNavItem(
    var title: String,
    var icon: Int,
    var route: String
) {
    object Home :
        BottomNavItem(
            title = "Home",
            icon = R.drawable.ic_home,
            route = Screens.HomeView.name
        )

    object Calendar :
        BottomNavItem(
            title = "Calendar",
            icon = R.drawable.ic_calendar,
            route = Screens.CalendarView.name
        )

    object Settings :
        BottomNavItem(
            title = "Settings",
            icon = R.drawable.ic_settings,
            route = Screens.SettingsView.name
        )

    object Bodyweight :
        BottomNavItem(
            title = "bodyweight_view",
            icon = R.drawable.ic_settings,
            route = Screens.BodyweightView.name
        )

    object MainView :
        BottomNavItem(
            title = "main_view",
            icon = R.drawable.ic_settings,
            route = Screens.MainView.name
        )
}
