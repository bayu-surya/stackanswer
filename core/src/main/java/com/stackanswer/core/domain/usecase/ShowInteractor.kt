package com.stackanswer.core.domain.usecase

import com.stackanswer.core.domain.repository.IShowRepository
import com.stackanswer.core.source.Resource
import com.stackanswer.core.source.local.room.show.ShowPopular
import io.reactivex.Flowable

class ShowInteractor(private val movieRepository: IShowRepository): ShowUseCaseKt {

    override fun getAllTourism(): Flowable<Resource<List<ShowPopular>>> = movieRepository.getAllTourism()
}