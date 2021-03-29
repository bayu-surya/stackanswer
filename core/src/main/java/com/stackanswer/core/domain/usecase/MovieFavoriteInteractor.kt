package com.stackanswer.core.domain.usecase

import com.stackanswer.core.domain.repository.IMovieFavoriteRepository
import com.stackanswer.core.source.local.room.moviefavorite.MovieFavorite

class MovieFavoriteInteractor(private val movieRepository: IMovieFavoriteRepository): MovieFavoriteUseCaseKt {

    override fun getAllTourism() = movieRepository.getAllTourism()

    override fun setFavoriteTourism(tourism: MovieFavorite, state: Boolean) {
        movieRepository.setFavoriteTourism(tourism, state)
    }

    override fun updateFavoriteTourism(tourism: MovieFavorite, state: Boolean) {
        movieRepository.updateFavoriteTourism(tourism, state)
    }

    override fun deleteFavoriteTourism(tourism: MovieFavorite, state: Boolean) {
        movieRepository.deleteFavoriteTourism(tourism, state)
    }

//    override fun deleteAllFavoriteTourism() {
//        movieRepository.deleteAllFavoriteTourism()
//    }
}