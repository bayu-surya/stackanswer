package com.stackanswer.viewmodel

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.stackanswer.core.domain.usecase.ShowUseCaseKt

class ShowViewModelKt (tourismUseCase: ShowUseCaseKt) : ViewModel() {
    val tourism = LiveDataReactiveStreams.fromPublisher(tourismUseCase.getAllTourism())
}
