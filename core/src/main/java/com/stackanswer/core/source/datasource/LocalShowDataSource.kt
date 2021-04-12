package com.stackanswer.core.source.datasource

import com.stackanswer.core.source.local.room.show.ShowDao
import com.stackanswer.core.source.local.room.show.ShowPopular
import io.reactivex.Flowable

class LocalShowDataSource(private val tourismDao: ShowDao) {

    fun getAllTourism(): Flowable<List<ShowPopular>> = tourismDao.getAllTourism()

    fun insertTourism(tourismList: List<ShowPopular>) =
        tourismDao.insertTourism(tourismList)
}