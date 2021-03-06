package com.stackanswer.core.source.local.room.movie

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface MovieDao {

    @Query("SELECT * FROM moviepopular")
    fun getAllTourism(): Flowable<List<MoviePopular>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTourism(tourism: List<MoviePopular>): Completable

    @Update
    fun updateFavoriteTourism(tourism: MoviePopular)
}
