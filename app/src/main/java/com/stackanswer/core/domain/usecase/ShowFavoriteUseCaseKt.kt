package com.stackanswer.core.domain.usecase

import com.stackanswer.source.local.room.showfavorite.ShowFavorite
import io.reactivex.Flowable

interface ShowFavoriteUseCaseKt {
    fun getAllTourism(): Flowable<List<ShowFavorite>>
    fun setFavoriteTourism(tourism: ShowFavorite, state: Boolean)
    fun updateFavoriteTourism(tourism: ShowFavorite, state: Boolean)
    fun deleteFavoriteTourism(tourism: ShowFavorite, state: Boolean)
    fun deleteAllFavoriteTourism()
}