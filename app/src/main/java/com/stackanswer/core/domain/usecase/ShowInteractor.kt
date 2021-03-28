package com.stackanswer.core.domain.usecase

import com.stackanswer.core.domain.repository.IShowRepository

class ShowInteractor(private val movieRepository: IShowRepository): ShowUseCaseKt {

    override fun getAllTourism() = movieRepository.getAllTourism()

//    override fun getFavoriteTourism() = movieRepository.getFavoriteTourism()
//
//    override fun setFavoriteTourism(tourism: ShowPopular, state: Boolean) = movieRepository.setFavoriteTourism(tourism, state)
}