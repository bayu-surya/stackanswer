package com.stackanswer.source.datasource

import com.stackanswer.source.local.room.movie.MovieDao
import com.stackanswer.source.local.room.movie.MoviePopular
import io.reactivex.Flowable

class LocalDataSource(private val tourismDao: MovieDao) {

//    companion object {
//        private var instance: LocalDataSource? = null
//
//        fun getInstance(tourismDao: MovieDao): LocalDataSource =
//            instance ?: synchronized(this) {
//                instance ?: LocalDataSource(tourismDao)
//            }
//    }

    fun getAllTourism(): Flowable<List<MoviePopular>> = tourismDao.getAllTourism()

    fun insertTourism(tourismList: List<MoviePopular>) =
        tourismDao.insertTourism(tourismList)

//    fun getFavoriteTourism(): Flowable<List<MoviePopular>> = tourismDao.getFavoriteTourism()

//    fun setFavoriteTourism(tourism: MoviePopular, newState: Boolean) {
//        tourism.isFavorite = newState
//        tourismDao.updateFavoriteTourism(tourism)
//    }
}