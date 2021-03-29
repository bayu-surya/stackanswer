package com.stackanswer.core.domain.usecase

import com.stackanswer.core.source.Resource
import com.stackanswer.core.source.local.room.movie.MoviePopular
import io.reactivex.Flowable

interface DetailMovieUseCaseKt {
    fun getAllTourism(id: String): Flowable<Resource<List<MoviePopular>>>
//    fun getFavoriteTourism(): Flowable<List<MoviePopular>>
//    fun setFavoriteTourism(tourism: MoviePopular, state: Boolean)
}