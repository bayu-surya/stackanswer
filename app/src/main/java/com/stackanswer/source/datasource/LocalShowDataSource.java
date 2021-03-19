package com.stackanswer.source.datasource;

import androidx.paging.DataSource;

import com.stackanswer.source.local.room.showfavorite.ShowFavorite;
import com.stackanswer.source.local.room.showfavorite.ShowFavoriteDao;

public class LocalShowDataSource {

    private static LocalShowDataSource INSTANCE;
    private final ShowFavoriteDao mShowFavoriteDao;

    private LocalShowDataSource(ShowFavoriteDao mShowFavoriteDao) {
        this.mShowFavoriteDao = mShowFavoriteDao;
    }

    public static LocalShowDataSource getInstance(ShowFavoriteDao academyDao) {
        if (INSTANCE == null) {
            INSTANCE = new LocalShowDataSource(academyDao);
        }
        return INSTANCE;
    }

    public DataSource.Factory<Integer, ShowFavorite> getAllShow() {
        return mShowFavoriteDao.getAllShow();
    }

    public void update(ShowFavorite courses) {
        mShowFavoriteDao.update(courses);
    }

    public void delete(ShowFavorite courses) {
        mShowFavoriteDao.delete(courses);
    }

    public void insert(ShowFavorite courses) {
        mShowFavoriteDao.insert(courses);
    }

    public void deleteAllShow() {
        mShowFavoriteDao.deleteAllShow();
    }
}