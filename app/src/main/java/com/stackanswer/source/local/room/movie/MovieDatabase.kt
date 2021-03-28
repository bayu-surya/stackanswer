package com.stackanswer.source.local.room.movie

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MoviePopular::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun tourismDao(): MovieDao

//    companion object {
//        @Volatile
//        private var INSTANCE: MovieDatabase? = null
//
//        fun getInstance(context: Context): MovieDatabase =
//            INSTANCE ?: synchronized(this) {
//            val instance = Room.databaseBuilder(context.applicationContext,
//                MovieDatabase::class.java, "movie2_database")
//                .fallbackToDestructiveMigration()
//                .build()
//            INSTANCE = instance
//            instance
//        }
//    }
}