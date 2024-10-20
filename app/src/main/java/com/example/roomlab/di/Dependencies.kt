package com.example.roomlab.di

import android.content.Context
import androidx.room.Room
import com.example.roomlab.data.local.MelodyDB

object Dependencies {
    private var database: MelodyDB? = null

    private fun buildDatabase(context: Context): MelodyDB {
        return Room.databaseBuilder(
            context,
            MelodyDB::class.java,
            "uvg.db"
        ).build()
    }

    fun provideDatabase(context: Context): MelodyDB {
        return database ?: synchronized(this) {
            database ?: buildDatabase(context).also { database = it }
        }
    }
}
