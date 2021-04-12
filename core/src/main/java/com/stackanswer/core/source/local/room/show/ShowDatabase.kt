package com.stackanswer.core.source.local.room.show

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ShowPopular::class], version = 1, exportSchema = false)
abstract class ShowDatabase : RoomDatabase() {

    abstract fun tourismDao(): ShowDao
}