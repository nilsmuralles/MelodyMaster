package com.example.roomlab.domain.model

import androidx.compose.ui.graphics.Color

data class Song(
    val id: Int,
    val name: String,
    val artistName: String,
    val color: Color,
    val genre: String,
    val isFavorite: Boolean
)

fun List<Song>.sortAlphabetically(): List<Song> {
    return this.sortedWith(compareByDescending<Song> { it.isFavorite }.thenBy { it.name })
}