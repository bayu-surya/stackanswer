package com.stackanswer.core.domain.usecase

import com.stackanswer.core.domain.repository.IDetailShowRepository

class DetailShowInteractor(private val movieRepository: IDetailShowRepository): DetailShowUseCaseKt {

    override fun getAllTourism(id: String) = movieRepository.getAllTourism(id)
}