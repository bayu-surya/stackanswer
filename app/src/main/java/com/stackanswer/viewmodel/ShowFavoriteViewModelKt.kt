package com.stackanswer.viewmodel

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.stackanswer.core.domain.usecase.ShowFavoriteUseCaseKt
import com.stackanswer.core.source.local.room.showfavorite.ShowFavorite

class ShowFavoriteViewModelKt (tourismUseCase: ShowFavoriteUseCaseKt) : ViewModel() {

    private val  to = tourismUseCase

    val tourism = LiveDataReactiveStreams.fromPublisher(tourismUseCase.getAllTourism())

    fun setFavoriteTourism(tourism: ShowFavorite, newStatus:Boolean){
        to.setFavoriteTourism(tourism, newStatus)
    }

    fun updateFavoriteTourism(tourism: ShowFavorite, newStatus:Boolean){
        to.updateFavoriteTourism(tourism, newStatus)
    }

    fun deleteFavoriteTourism(tourism: ShowFavorite, newStatus:Boolean){
        to.deleteFavoriteTourism(tourism, newStatus)
    }

}
