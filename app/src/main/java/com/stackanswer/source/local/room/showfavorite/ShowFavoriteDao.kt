package com.stackanswer.source.local.room.showfavorite

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface ShowFavoriteDao {

    @Query("SELECT * FROM show_table ORDER BY id ASC")
    fun getAllShow(): Flowable<List<ShowFavorite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favorite: ShowFavorite): Completable

    @Update
    fun update(favorite: ShowFavorite?)

    @Delete
    fun delete(favorite: ShowFavorite?)

    @Query("DELETE FROM show_table")
    fun deleteAllShow()
}