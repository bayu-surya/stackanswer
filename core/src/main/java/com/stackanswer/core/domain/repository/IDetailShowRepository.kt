package com.stackanswer.core.domain.repository

import com.stackanswer.core.source.Resource
import com.stackanswer.core.source.local.room.show.ShowPopular
import io.reactivex.Flowable

interface IDetailShowRepository {

    fun getAllTourism(id: String): Flowable<Resource<List<ShowPopular>>>

//    fun getFavoriteTourism(): Flowable<List<ShowPopular>>
//
//    fun setFavoriteTourism(tourism: ShowPopular, state: Boolean)

}