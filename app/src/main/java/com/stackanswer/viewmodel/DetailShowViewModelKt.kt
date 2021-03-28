package com.stackanswer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.stackanswer.core.domain.usecase.DetailShowUseCaseKt
import com.stackanswer.source.Resource
import com.stackanswer.source.local.room.show.ShowPopular

class DetailShowViewModelKt (tourismUseCase: DetailShowUseCaseKt) : ViewModel() {
     private val  to = tourismUseCase

    fun tourism(id: String ): LiveData<Resource<List<ShowPopular>>> {
        return LiveDataReactiveStreams.fromPublisher(to.getAllTourism(id))
    }
}

