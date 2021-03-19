package com.stackanswer.source.datasource;

import androidx.paging.DataSource;

import com.stackanswer.source.local.room.moviefavorite.MovieFavorite;
import com.stackanswer.source.local.room.moviefavorite.MovieFavoriteDao;

public class LocalMovieDataSource {

    private static LocalMovieDataSource INSTANCE;
    private final MovieFavoriteDao mMovieFavoriteDao;

    private LocalMovieDataSource(MovieFavoriteDao mMovieFavoriteDao) {
        this.mMovieFavoriteDao = mMovieFavoriteDao;
    }

    public static LocalMovieDataSource getInstance(MovieFavoriteDao academyDao) {
        if (INSTANCE == null) {
            INSTANCE = new LocalMovieDataSource(academyDao);
        }
        return INSTANCE;
    }

    public DataSource.Factory<Integer, MovieFavorite> getAllMovie() {
        return mMovieFavoriteDao.getAllMovie();
    }

    public void update(MovieFavorite courses) {
        mMovieFavoriteDao.update(courses);
    }

    public void delete(MovieFavorite courses) {
        mMovieFavoriteDao.delete(courses);
    }

    public void insert(MovieFavorite courses) {
        mMovieFavoriteDao.insert(courses);
    }

    public void deleteAllMovie() {
        mMovieFavoriteDao.deleteAllMovie();
    }
}