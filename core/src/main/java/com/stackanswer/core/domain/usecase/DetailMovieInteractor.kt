package com.stackanswer.core.domain.usecase

import com.stackanswer.core.domain.repository.IDetailMovieRepository

class DetailMovieInteractor(private val movieRepository: IDetailMovieRepository): DetailMovieUseCaseKt {

    override fun getAllTourism(id: String) = movieRepository.getAllTourism(id)
}