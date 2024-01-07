package com.alejandroglzdev.takeyourcreatine.ui.component.items

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.alejandroglzdev.takeyourcreatine.domain.BottomNavItem
import com.alejandroglzdev.takeyourcreatine.ui.theme.Accent
import com.alejandroglzdev.takeyourcreatine.ui.theme.PrimaryDark
import com.alejandroglzdev.takeyourcreatine.ui.theme.Secondary

@Composable
fun BottomAppBar() {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Calendar,
        BottomNavItem.Settings
    )

    NavigationBar(containerColor = PrimaryDark) {
        items.forEach { items ->
            AddItem(screen = items)
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomNavItem
) {
    NavigationBarItem(
        label = {
            Text(
                text = screen.title,
                style = MaterialTheme.typography.labelSmall
            )
        },
        icon = {
            Icon(
                painterResource(id = screen.icon),
                contentDescription = screen.title,
                modifier = Modifier.size(24.dp)
            )
        },
        selected = false,
        alwaysShowLabel = true,
        onClick = { /*TODO*/ },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Accent,
            selectedTextColor = Accent,
            indicatorColor = Secondary,
            unselectedIconColor = Secondary,
            unselectedTextColor = Secondary
        )
    )

}



