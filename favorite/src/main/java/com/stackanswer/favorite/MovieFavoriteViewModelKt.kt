package com.stackanswer.favorite

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.stackanswer.core.domain.usecase.MovieFavoriteUseCaseKt
import com.stackanswer.core.source.local.room.moviefavorite.MovieFavorite

class MovieFavoriteViewModelKt (tourismUseCase: MovieFavoriteUseCaseKt) : ViewModel() {

    private val  to = tourismUseCase

    val tourism = LiveDataReactiveStreams.fromPublisher(tourismUseCase.getAllTourism())

    fun setFavoriteTourism(tourism: MovieFavorite, newStatus:Boolean){
        to.setFavoriteTourism(tourism, newStatus)
    }

    fun updateFavoriteTourism(tourism: MovieFavorite, newStatus:Boolean){
        to.updateFavoriteTourism(tourism, newStatus)
    }

    fun deleteFavoriteTourism(tourism: MovieFavorite, newStatus:Boolean){
        to.deleteFavoriteTourism(tourism, newStatus)
    }

//    fun deleteAllFavoriteTourism(){
//        to.deleteAllFavoriteTourism()
//    }

}
