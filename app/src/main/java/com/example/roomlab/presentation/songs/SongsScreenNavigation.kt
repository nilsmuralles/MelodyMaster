package com.example.roomlab.presentation.songs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object SongsScreenDestination

fun NavGraphBuilder.songsScreen() {
    composable<SongsScreenDestination>{
        SongsRoute()
    }
}