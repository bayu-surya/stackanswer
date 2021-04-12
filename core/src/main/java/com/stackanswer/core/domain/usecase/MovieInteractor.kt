package com.stackanswer.core.domain.usecase

import com.stackanswer.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCaseKt {

    override fun getAllTourism() = movieRepository.getAllTourism()
}