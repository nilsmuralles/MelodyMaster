package com.example.roomlab.presentation.songs

import com.example.roomlab.domain.model.Song

data class SongsScreenState(
    val isLoading: Boolean = true,
    val songs: List<Song> = emptyList()
)
