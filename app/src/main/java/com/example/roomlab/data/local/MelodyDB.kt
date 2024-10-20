package com.example.roomlab.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomlab.data.local.dao.ArtistDAO
import com.example.roomlab.data.local.dao.SongDAO
import com.example.roomlab.data.local.entity.ArtistEntity
import com.example.roomlab.data.local.entity.SongEntity

@Database(
    entities = [
        ArtistEntity::class,
        SongEntity::class
    ],
    version = 1
)
abstract class MelodyDB: RoomDatabase() {
    abstract fun artistDao(): ArtistDAO
    abstract fun songDao(): SongDAO
}