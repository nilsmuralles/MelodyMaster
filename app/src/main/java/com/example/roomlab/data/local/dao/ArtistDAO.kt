package com.example.roomlab.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.roomlab.data.local.entity.ArtistEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtistDAO {
    @Query("SELECT * FROM ArtistEntity")
    fun getAllArtists(): Flow<List<ArtistEntity>>
    @Insert
    suspend fun insertAll(artists: List<ArtistEntity>)
}