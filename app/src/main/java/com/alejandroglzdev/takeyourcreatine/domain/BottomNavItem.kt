package com.alejandroglzdev.takeyourcreatine.domain

import com.alejandroglzdev.takeyourcreatine.R

sealed class BottomNavItem(
    var title: String,
    var icon: Int,
) {
    object Home :
            BottomNavItem(
                title = "Home",
                icon = R.drawable.ic_home
            )

    object Calendar :
        BottomNavItem(
            title = "Calendar",
            icon = R.drawable.ic_calendar
        )

    object Settings :
        BottomNavItem(
            title = "Settings",
            icon = R.drawable.ic_settings
        )
}
