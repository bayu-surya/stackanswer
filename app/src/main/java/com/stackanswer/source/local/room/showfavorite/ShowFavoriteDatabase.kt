package com.stackanswer.source.local.room.showfavorite

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ShowFavorite::class], version = 1, exportSchema = false)
abstract class ShowFavoriteDatabase: RoomDatabase() {

    abstract fun showDao(): ShowFavoriteDao

//    companion object {
//        @Volatile
//        private var INSTANCE: ShowFavoriteDatabase? = null
//
//        fun getInstance(context: Context): ShowFavoriteDatabase =
//                INSTANCE ?: synchronized(this) {
//                    val instance = Room.databaseBuilder(context.applicationContext,
//                            ShowFavoriteDatabase::class.java, "show_database")
//                            .fallbackToDestructiveMigration()
//                            .build()
//                    INSTANCE = instance
//                    instance
//                }
//    }
}