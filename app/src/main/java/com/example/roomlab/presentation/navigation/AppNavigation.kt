package com.example.roomlab.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.roomlab.presentation.artists.artistsScreen
import com.example.roomlab.presentation.songs.SongsScreenDestination
import com.example.roomlab.presentation.songs.songsScreen

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavBar(
                checkItemSelected = { destination ->
                    currentDestination?.hierarchy?.any { it.hasRoute(destination::class)} ?: false
                },
                onNavItemClick = { destination ->
                    navController.navigate(destination) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = SongsScreenDestination,
            modifier = modifier
                .padding(innerPadding)
        ) {
            songsScreen()
            artistsScreen()
        }
    }
}