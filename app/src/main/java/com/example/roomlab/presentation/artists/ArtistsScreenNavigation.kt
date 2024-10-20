package com.example.roomlab.presentation.artists

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object ArtistsScreenDestination

fun NavGraphBuilder.artistsScreen() {
    composable<ArtistsScreenDestination> {
        ArtistsRoute()
    }
}