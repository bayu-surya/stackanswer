package com.stackanswer.core.domain.usecase

import com.stackanswer.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCaseKt {

    override fun getAllTourism() = movieRepository.getAllTourism()

//    override fun getFavoriteTourism() = movieRepository.getFavoriteTourism()
//
//    override fun setFavoriteTourism(tourism: MoviePopular, state: Boolean) = movieRepository.setFavoriteTourism(tourism, state)
}