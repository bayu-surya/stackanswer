package com.stackanswer.source.local.room.moviefavorite

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface MovieFavoriteDao {

    @Query("SELECT * FROM movie_table ORDER BY id ASC")
    fun getAllMovie(): Flowable<List<MovieFavorite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieFavorite: MovieFavorite): Completable

    @Update
    fun update(movieFavorite: MovieFavorite?)

    @Delete
    fun delete(movieFavorite: MovieFavorite?)

    @Query("DELETE FROM movie_table")
    fun deleteAllMovie()
}