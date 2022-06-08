package com.odhiambodevelopers.easyeats

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.odhiambodevelopers.easyeats.screens.destinations.Destination
import com.odhiambodevelopers.easyeats.screens.destinations.FavoritesDestination
import com.odhiambodevelopers.easyeats.screens.destinations.MainScreenDestination
import com.odhiambodevelopers.easyeats.screens.destinations.UserScreenDestination

sealed class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val destination: Destination
) {
    object MainScreen : BottomNavItem(
        title = "Home",
        icon = Icons.Default.Home,
        destination = MainScreenDestination
    )
    object FavoriteScreen:BottomNavItem(
        title = "Favorite",
        icon = Icons.Default.Favorite,
        destination = FavoritesDestination
    )
    object UserScreen:BottomNavItem(
        title = "User",
        icon = Icons.Default.Person,
        destination = UserScreenDestination
    )

}