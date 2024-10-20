package com.example.roomlab.domain.model

data class Artist(
    val name: String,
    val songs: List<Song>,
    val monthlyListeners: Int
)
