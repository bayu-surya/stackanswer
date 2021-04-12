package com.stackanswer.core.domain.repository

import com.stackanswer.core.source.Resource
import com.stackanswer.core.source.local.room.movie.MoviePopular
import io.reactivex.Flowable

interface IMovieRepository {

    fun getAllTourism(): Flowable<Resource<List<MoviePopular>>>

}