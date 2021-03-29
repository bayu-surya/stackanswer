package com.stackanswer.core.domain.usecase

import com.stackanswer.core.domain.repository.IDetailMovieRepository

class DetailMovieInteractor(private val movieRepository: IDetailMovieRepository): DetailMovieUseCaseKt {

    override fun getAllTourism(id: String) = movieRepository.getAllTourism(id)

//    override fun getFavoriteTourism() = movieRepository.getFavoriteTourism()
//
//    override fun setFavoriteTourism(tourism: MoviePopular, state: Boolean) = movieRepository.setFavoriteTourism(tourism, state)
}