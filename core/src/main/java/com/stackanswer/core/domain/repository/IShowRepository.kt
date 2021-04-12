package com.stackanswer.core.domain.repository

import com.stackanswer.core.source.Resource
import com.stackanswer.core.source.local.room.show.ShowPopular
import io.reactivex.Flowable

interface IShowRepository {

    fun getAllTourism(): Flowable<Resource<List<ShowPopular>>>

}