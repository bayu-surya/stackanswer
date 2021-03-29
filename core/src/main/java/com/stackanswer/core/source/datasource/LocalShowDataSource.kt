package com.stackanswer.core.source.datasource

import com.stackanswer.core.source.local.room.show.ShowDao
import com.stackanswer.core.source.local.room.show.ShowPopular
import io.reactivex.Flowable

class LocalShowDataSource(private val tourismDao: ShowDao) {

    companion object {
        private var instance: LocalShowDataSource? = null

        fun getInstance(tourismDao: ShowDao): LocalShowDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalShowDataSource(tourismDao)
            }
    }

    fun getAllTourism(): Flowable<List<ShowPopular>> = tourismDao.getAllTourism()

    fun insertTourism(tourismList: List<ShowPopular>) =
        tourismDao.insertTourism(tourismList)

//    fun getFavoriteTourism(): Flowable<List<ShowPopular>> = tourismDao.getFavoriteTourism()

//    fun setFavoriteTourism(tourism: ShowPopular, newState: Boolean) {
//        tourism.isFavorite = newState
//        tourismDao.updateFavoriteTourism(tourism)
//    }
}