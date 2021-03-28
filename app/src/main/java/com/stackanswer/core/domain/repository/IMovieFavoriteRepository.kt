package com.stackanswer.core.domain.repository

import com.stackanswer.source.local.room.moviefavorite.MovieFavorite
import io.reactivex.Flowable

interface IMovieFavoriteRepository {

    fun getAllTourism(): Flowable<List<MovieFavorite>>
    fun setFavoriteTourism(tourism: MovieFavorite, state: Boolean)
    fun updateFavoriteTourism(tourism: MovieFavorite, state: Boolean)
    fun deleteFavoriteTourism(tourism: MovieFavorite, state: Boolean)
//    fun deleteAllFavoriteTourism()
}