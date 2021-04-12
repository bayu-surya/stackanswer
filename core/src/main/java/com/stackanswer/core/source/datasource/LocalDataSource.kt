package com.stackanswer.core.source.datasource

import com.stackanswer.core.source.local.room.movie.MovieDao
import com.stackanswer.core.source.local.room.movie.MoviePopular
import io.reactivex.Flowable

class LocalDataSource(private val tourismDao: MovieDao) {

    fun getAllTourism(): Flowable<List<MoviePopular>> = tourismDao.getAllTourism()

    fun insertTourism(tourismList: List<MoviePopular>) =
        tourismDao.insertTourism(tourismList)

}