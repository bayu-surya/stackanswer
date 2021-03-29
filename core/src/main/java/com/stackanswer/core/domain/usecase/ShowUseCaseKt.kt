package com.stackanswer.core.domain.usecase

import com.stackanswer.core.source.Resource
import com.stackanswer.core.source.local.room.show.ShowPopular
import io.reactivex.Flowable

interface ShowUseCaseKt {
    fun getAllTourism(): Flowable<Resource<List<ShowPopular>>>
//    fun getFavoriteTourism(): Flowable<List<ShowPopular>>
//    fun setFavoriteTourism(tourism: ShowPopular, state: Boolean)
}