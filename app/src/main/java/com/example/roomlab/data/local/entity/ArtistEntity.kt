package com.example.roomlab.data.local.entity

import ArtistDTO
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roomlab.data.local.dao.ArtistDAO
import com.example.roomlab.domain.model.Artist

@Entity
data class ArtistEntity(
    @PrimaryKey val id: String,
    val name: String,
    val monthlyListeners: Int
)

fun ArtistEntity.mapToModel(localSongs: List<SongEntity>): Artist {
    return Artist(
        name = name,
        songs = localSongs.map { localSong -> localSong.mapToModel() },
        monthlyListeners = monthlyListeners
    )
}

fun ArtistDTO.mapToEntity(): ArtistEntity {
    return ArtistEntity(
        id = id,
        name = name,
        monthlyListeners = monthlyListeners
    )
}
