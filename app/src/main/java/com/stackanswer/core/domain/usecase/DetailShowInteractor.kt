package com.stackanswer.core.domain.usecase

import com.stackanswer.core.domain.repository.IDetailShowRepository
import com.stackanswer.source.local.room.show.ShowPopular

class DetailShowInteractor(private val movieRepository: IDetailShowRepository): DetailShowUseCaseKt {

    override fun getAllTourism(id: String) = movieRepository.getAllTourism(id)

    override fun getFavoriteTourism() = movieRepository.getFavoriteTourism()

    override fun setFavoriteTourism(tourism: ShowPopular, state: Boolean) = movieRepository.setFavoriteTourism(tourism, state)
}