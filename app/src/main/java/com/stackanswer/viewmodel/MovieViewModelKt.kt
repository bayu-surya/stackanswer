package com.stackanswer.viewmodel

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.stackanswer.core.domain.usecase.MovieUseCaseKt

class MovieViewModelKt (tourismUseCase: MovieUseCaseKt) : ViewModel() {
    val tourism = LiveDataReactiveStreams.fromPublisher(tourismUseCase.getAllTourism())
}
