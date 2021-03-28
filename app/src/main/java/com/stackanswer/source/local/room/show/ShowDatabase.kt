package com.stackanswer.source.local.room.show

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ShowPopular::class], version = 1, exportSchema = false)
abstract class ShowDatabase : RoomDatabase() {

    abstract fun tourismDao(): ShowDao

//    companion object {
//        @Volatile
//        private var INSTANCE: ShowDatabase? = null
//
//        fun getInstance(context: Context): ShowDatabase =
//            INSTANCE ?: synchronized(this) {
//            val instance = Room.databaseBuilder(context.applicationContext,
//                ShowDatabase::class.java, "show2_database")
//                .fallbackToDestructiveMigration()
//                .build()
//            INSTANCE = instance
//            instance
//        }
//    }
}