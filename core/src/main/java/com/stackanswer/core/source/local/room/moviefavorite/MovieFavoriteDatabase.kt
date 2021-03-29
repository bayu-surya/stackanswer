package com.stackanswer.core.source.local.room.moviefavorite

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieFavorite::class], version = 1, exportSchema = false)
abstract class MovieFavoriteDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieFavoriteDao

//    companion object {
//        @Volatile
//        private var INSTANCE: MovieFavoriteDatabase? = null
//
//        fun getInstance(context: Context): MovieFavoriteDatabase =
//                INSTANCE ?: synchronized(this) {
//                    val instance = Room.databaseBuilder(context.applicationContext,
//                            MovieFavoriteDatabase::class.java, "movie_database")
//                            .fallbackToDestructiveMigration()
//                            .build()
//                    INSTANCE = instance
//                    instance
//                }
//    }
}