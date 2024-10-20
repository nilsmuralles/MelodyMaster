package com.example.roomlab.presentation.utilities

import androidx.compose.ui.graphics.Color

fun randomColor(): Color {
    return Color(
        (0..255).random().toFloat() / 255f,
        (0..255).random().toFloat() / 255f,
        (0..255).random().toFloat() / 255f,
        1f
    )
}