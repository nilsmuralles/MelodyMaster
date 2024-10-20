package com.example.roomlab.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.roomlab.data.local.entity.SongEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDAO {
    @Query("SELECT * FROM SongEntity")
    fun getAllSongs(): Flow<List<SongEntity>>
    @Insert
    suspend fun insertAll(songs: List<SongEntity>)
    @Query("UPDATE SongEntity SET isFavorite = :isFav WHERE id = :id")
    suspend fun updateFavorite(id: Int, isFav: Boolean)
    @Query("SELECT * FROM SongEntity WHERE artistID = :artistID")
    fun getSongsFromArtist(artistID: String): Flow<List<SongEntity>>
}