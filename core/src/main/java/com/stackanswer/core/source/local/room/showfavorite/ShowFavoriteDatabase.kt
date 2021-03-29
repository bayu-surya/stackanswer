package com.stackanswer.core.source.local.room.showfavorite

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ShowFavorite::class], version = 1, exportSchema = false)
abstract class ShowFavoriteDatabase: RoomDatabase() {

    abstract fun showDao(): ShowFavoriteDao
}