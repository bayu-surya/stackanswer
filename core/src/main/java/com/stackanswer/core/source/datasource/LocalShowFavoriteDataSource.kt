package com.stackanswer.core.source.datasource

import com.stackanswer.core.source.local.room.showfavorite.ShowFavorite
import com.stackanswer.core.source.local.room.showfavorite.ShowFavoriteDao
import io.reactivex.Flowable

class LocalShowFavoriteDataSource(private val tourismDao: ShowFavoriteDao) {

    companion object {
        private var instance: LocalShowFavoriteDataSource? = null

        fun getInstance(tourismDao: ShowFavoriteDao): LocalShowFavoriteDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalShowFavoriteDataSource(tourismDao)
            }
    }

    fun getAllTourism(): Flowable<List<ShowFavorite>> = tourismDao.getAllShow()

    fun insertTourism(tourismList: ShowFavorite) =
        tourismDao.insert(tourismList)

    fun update(courses: ShowFavorite?) {
        tourismDao.update(courses)
    }

    fun delete(courses: ShowFavorite?) {
        tourismDao.delete(courses)
    }

//    fun deleteAllMovie() {
//        tourismDao.deleteAllShow()
//    }

//    fun getFavoriteTourism(): Flowable<List<ShowFavoritePopular>> = tourismDao.getFavoriteTourism()
//    fun setFavoriteTourism(tourism: ShowFavoritePopular, newState: Boolean) {
//        tourism.isFavorite = newState
//        tourismDao.updateFavoriteTourism(tourism)
//    }
}