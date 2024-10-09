package com.example.sdmw.Views

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.example.sdmw.R

@Composable
fun BottomBar(
    onIconClicked: (String) -> Unit,
    currentDestination: String
) {
    NavigationBar(
        modifier = Modifier.shadow(elevation = 8.dp,
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
    ) {
        NavigationBarItem(
            selected = currentDestination == "homeScreen",
            onClick = { onIconClicked("homeScreen") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_home),
                    contentDescription = "Home",
                    tint = if (currentDestination == "homeScreen") Color(0xFF7C46D5) else Color.Unspecified
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF7C46D5), // Selected icon color
                unselectedIconColor = Color.Unspecified // Unselected icon color
            )
        )

        NavigationBarItem(
            selected = currentDestination == "purchaseScreen",
            onClick = { onIconClicked("purchaseScreen") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bag),
                    contentDescription = "Purchase",
                    tint = if (currentDestination == "purchaseScreen") Color(0xFF7C46D5) else Color.Unspecified
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF7C46D5),
                unselectedIconColor = Color.Unspecified
            )
        )

        NavigationBarItem(
            selected = currentDestination == "expenseScreen",
            onClick = { onIconClicked("expenseScreen") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_wallet),
                    contentDescription = "Expenses",
                    tint = if (currentDestination == "expenseScreen") Color(0xFF7C46D5) else Color.Unspecified
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF7C46D5),
                unselectedIconColor = Color.Unspecified
            )
        )
        NavigationBarItem(
            selected = currentDestination == "staffScreen",
            onClick = { onIconClicked("staffScreen") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_staffusers),
                    contentDescription = "Expenses",
                    tint = if (currentDestination == "staffScreen") Color(0xFF7C46D5) else Color.Unspecified
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF7C46D5),
                unselectedIconColor = Color.Unspecified
            )
        )
    }
}


@Preview
@Composable
fun previeww(){
    BottomBar({},"homeScreen")
}