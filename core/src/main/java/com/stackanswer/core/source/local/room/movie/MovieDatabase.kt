package com.stackanswer.core.source.local.room.movie

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MoviePopular::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun tourismDao(): MovieDao
}