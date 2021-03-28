package com.stackanswer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.stackanswer.core.domain.usecase.DetailMovieUseCaseKt
import com.stackanswer.source.Resource
import com.stackanswer.source.local.room.movie.MoviePopular

class DetailMovieViewModelKt (tourismUseCase: DetailMovieUseCaseKt) : ViewModel() {
    private val  to = tourismUseCase

    fun tourism(id: String ): LiveData<Resource<List<MoviePopular>>> {
        return LiveDataReactiveStreams.fromPublisher(to.getAllTourism(id))
    }
}

