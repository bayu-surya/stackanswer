package com.stackanswer.core.domain.usecase

import com.stackanswer.core.source.Resource
import com.stackanswer.core.source.local.room.show.ShowPopular
import io.reactivex.Flowable

interface DetailShowUseCaseKt {
    fun getAllTourism(id: String): Flowable<Resource<List<ShowPopular>>>
}