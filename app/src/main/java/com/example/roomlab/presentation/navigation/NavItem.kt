package com.example.roomlab.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.roomlab.presentation.artists.ArtistsScreenDestination
import com.example.roomlab.presentation.songs.SongsScreenDestination

data class NavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val destination: Any,
)

val navigationItems = listOf(
    NavItem(
        title = "Explorar",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        destination = SongsScreenDestination
    ),
    NavItem(
        title = "Artistas",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
        destination = ArtistsScreenDestination
    )
)