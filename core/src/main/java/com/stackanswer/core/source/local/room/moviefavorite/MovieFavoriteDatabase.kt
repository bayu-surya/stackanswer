package com.stackanswer.core.source.local.room.moviefavorite

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieFavorite::class], version = 1, exportSchema = false)
abstract class MovieFavoriteDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieFavoriteDao
}