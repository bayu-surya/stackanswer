package com.stackanswer.core.domain.repository

import com.stackanswer.source.Resource
import com.stackanswer.source.local.room.show.ShowPopular
import io.reactivex.Flowable

interface IShowRepository {

    fun getAllTourism(): Flowable<Resource<List<ShowPopular>>>

    fun getFavoriteTourism(): Flowable<List<ShowPopular>>

    fun setFavoriteTourism(tourism: ShowPopular, state: Boolean)

}