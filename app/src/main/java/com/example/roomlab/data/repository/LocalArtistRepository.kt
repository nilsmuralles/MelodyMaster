package com.example.roomlab.data.repository

import DataGenerator
import com.example.roomlab.data.local.dao.ArtistDAO
import com.example.roomlab.data.local.dao.SongDAO
import com.example.roomlab.data.local.entity.mapToEntity
import com.example.roomlab.data.local.entity.mapToModel
import com.example.roomlab.domain.model.Artist
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

class LocalArtistRepository (
    private val artistDAO: ArtistDAO,
    private val songDAO: SongDAO
){
     @OptIn(ExperimentalCoroutinesApi::class)
     fun getArtists(): Flow<List<Artist>> =
         artistDAO.getAllArtists().flatMapLatest { localArtists ->
             combine(
                 localArtists.map { localArtist ->
                     songDAO.getSongsFromArtist(localArtist.id).map { localArtistSongs ->
                         localArtist.mapToModel(localArtistSongs)
                     }
                 }
             ) { artists ->
                 artists.toList()
             }
         }

    suspend fun populateLocalArtistDatabase() {
        val localArtists = artistDAO.getAllArtists().first()

        if (localArtists.isEmpty()) {
            val remoteArtists = DataGenerator.getArtists()
            val artistsEntities = remoteArtists.map { remoteArtist ->
                remoteArtist.mapToEntity()
            }
            artistDAO.insertAll(artistsEntities)
        }
    }
}