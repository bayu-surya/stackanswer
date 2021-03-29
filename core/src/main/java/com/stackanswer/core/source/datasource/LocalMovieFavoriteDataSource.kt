package com.stackanswer.core.source.datasource

import com.stackanswer.core.source.local.room.moviefavorite.MovieFavorite
import com.stackanswer.core.source.local.room.moviefavorite.MovieFavoriteDao
import io.reactivex.Flowable

class LocalMovieFavoriteDataSource(private val tourismDao: MovieFavoriteDao) {

    companion object {
        private var instance: LocalMovieFavoriteDataSource? = null

        fun getInstance(tourismDao: MovieFavoriteDao): LocalMovieFavoriteDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalMovieFavoriteDataSource(tourismDao)
            }
    }

    fun getAllTourism(): Flowable<List<MovieFavorite>> = tourismDao.getAllMovie()

    fun insertTourism(tourismList: MovieFavorite) =
        tourismDao.insert(tourismList)

    fun update(courses: MovieFavorite?) {
        tourismDao.update(courses)
    }

    fun delete(courses: MovieFavorite?) {
        tourismDao.delete(courses)
    }

//    fun deleteAllMovie() {
//        tourismDao.deleteAllMovie()
//    }

//    fun getFavoriteTourism(): Flowable<List<MovieFavoritePopular>> = tourismDao.getFavoriteTourism()
//    fun setFavoriteTourism(tourism: MovieFavoritePopular, newState: Boolean) {
//        tourism.isFavorite = newState
//        tourismDao.updateFavoriteTourism(tourism)
//    }
}