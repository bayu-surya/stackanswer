package com.stackanswer.core.source.datasource

import com.stackanswer.core.source.local.room.showfavorite.ShowFavorite
import com.stackanswer.core.source.local.room.showfavorite.ShowFavoriteDao
import io.reactivex.Flowable

class LocalShowFavoriteDataSource(private val tourismDao: ShowFavoriteDao) {

    fun getAllTourism(): Flowable<List<ShowFavorite>> = tourismDao.getAllShow()

    fun insertTourism(tourismList: ShowFavorite) =
        tourismDao.insert(tourismList)

    fun update(courses: ShowFavorite?) {
        tourismDao.update(courses)
    }

    fun delete(courses: ShowFavorite?) {
        tourismDao.delete(courses)
    }
}