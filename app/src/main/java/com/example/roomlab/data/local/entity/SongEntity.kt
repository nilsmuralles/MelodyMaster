package com.example.roomlab.data.local.entity

import SongDTO
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roomlab.domain.model.Song
import com.example.roomlab.presentation.utilities.randomColor

@Entity
data class SongEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val isFavorite: Boolean,
    val artistID: String,
    val artistName: String,
    val genre: String
)

fun SongEntity.mapToModel(): Song {
    return Song(
        id = id,
        name = name,
        isFavorite = isFavorite,
        artistName = artistName,
        genre = genre,
        color = randomColor()
    )
}

fun SongDTO.mapToEntity(artistName: String): SongEntity {
    return SongEntity(
        id = id,
        name = name,
        artistID = artist_id,
        genre = genre,
        isFavorite = false,
        artistName = artistName
    )
}
