package com.stackanswer.core.source.local.room.show

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface ShowDao {

    @Query("SELECT * FROM showpopular")
    fun getAllTourism(): Flowable<List<ShowPopular>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTourism(tourism: List<ShowPopular>): Completable

    @Update
    fun updateFavoriteTourism(tourism: ShowPopular)
}
