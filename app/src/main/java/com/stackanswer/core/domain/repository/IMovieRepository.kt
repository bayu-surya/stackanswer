package com.stackanswer.core.domain.repository

import com.stackanswer.source.Resource
import com.stackanswer.source.local.room.movie.MoviePopular
import io.reactivex.Flowable

interface IMovieRepository {

    fun getAllTourism(): Flowable<Resource<List<MoviePopular>>>

//    fun getFavoriteTourism(): Flowable<List<MoviePopular>>
//
//    fun setFavoriteTourism(tourism: MoviePopular, state: Boolean)

}