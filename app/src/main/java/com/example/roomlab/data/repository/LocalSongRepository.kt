package com.example.roomlab.data.repository

import DataGenerator
import com.example.roomlab.data.local.dao.SongDAO
import com.example.roomlab.data.local.entity.mapToEntity
import com.example.roomlab.data.local.entity.mapToModel
import com.example.roomlab.domain.model.Song
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class LocalSongRepository(
    private val songDAO: SongDAO
) {
    fun getAllSongs(): Flow<List<Song>> =
        songDAO.getAllSongs().map { localSongs ->
            localSongs.map { localSong ->
                localSong.mapToModel()
            }
        }

    suspend fun setFavorite(songId: Int, isFavorite: Boolean) {
        songDAO.updateFavorite(
            id = songId,
            isFav = !isFavorite
        )
    }

    suspend fun populateLocalSongDatabase() {
        val localSongs = songDAO.getAllSongs().first()

        if (localSongs.isEmpty()) {
            val remoteSongs = DataGenerator.getSongs()
            val remoteArtists = DataGenerator.getArtists()

            val songEntities = remoteSongs.map { remoteSong ->
                val artistName = remoteArtists.find { artist ->
                    artist.id == remoteSong.artist_id
                }
                remoteSong.mapToEntity(artistName?.name ?: "Desconocido")
            }
            songDAO.insertAll(songEntities)
        }
    }
}