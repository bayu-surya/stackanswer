package com.stackanswer.source.local.room.showfavorite;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ShowFavoriteDao {

    @Query("SELECT * FROM show_table ORDER BY id ASC")
    DataSource.Factory<Integer, ShowFavorite> getAllShow();

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    void insert(ShowFavorite showFavorite);

    @Update
    void update(ShowFavorite showFavorite);

    @Delete
    void delete(ShowFavorite showFavorite);

    @Query("DELETE FROM show_table")
    void deleteAllShow();
}
