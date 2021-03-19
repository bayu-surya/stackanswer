package com.stackanswer.source.local.room.moviefavorite;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface MovieFavoriteDao {

    @Query("SELECT * FROM movie_table ORDER BY id ASC")
    DataSource.Factory<Integer, MovieFavorite> getAllMovie();

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    void insert(MovieFavorite movieFavorite);

    @Update
    void update(MovieFavorite movieFavorite);

    @Delete
    void delete(MovieFavorite movieFavorite);

    @Query("DELETE FROM movie_table")
    void deleteAllMovie();
}
