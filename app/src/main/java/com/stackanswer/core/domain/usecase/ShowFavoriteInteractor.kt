package com.stackanswer.core.domain.usecase

import com.stackanswer.core.domain.repository.IShowFavoriteRepository
import com.stackanswer.source.local.room.showfavorite.ShowFavorite

class ShowFavoriteInteractor(private val movieRepository: IShowFavoriteRepository): ShowFavoriteUseCaseKt {

    override fun getAllTourism() = movieRepository.getAllTourism()

    override fun setFavoriteTourism(tourism: ShowFavorite, state: Boolean) {
        movieRepository.setFavoriteTourism(tourism, state)
    }

    override fun updateFavoriteTourism(tourism: ShowFavorite, state: Boolean) {
        movieRepository.updateFavoriteTourism(tourism, state)
    }

    override fun deleteFavoriteTourism(tourism: ShowFavorite, state: Boolean) {
        movieRepository.deleteFavoriteTourism(tourism, state)
    }

//    override fun deleteAllFavoriteTourism() {
//        movieRepository.deleteAllFavoriteTourism()
//    }
}