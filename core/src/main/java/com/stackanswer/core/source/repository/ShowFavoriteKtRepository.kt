package com.stackanswer.core.source.repository

import com.stackanswer.core.domain.repository.IShowFavoriteRepository
import com.stackanswer.core.source.datasource.LocalShowFavoriteDataSource
import com.stackanswer.core.source.local.room.showfavorite.ShowFavorite
import com.stackanswer.core.utils.AppExecutors
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ShowFavoriteKtRepository(
    private val localDataSource: LocalShowFavoriteDataSource,
    private val appExecutors: AppExecutors
) : IShowFavoriteRepository {

    override fun getAllTourism(): Flowable<List<ShowFavorite>> {
        return localDataSource.getAllTourism()
    }

    override fun setFavoriteTourism(tourism: ShowFavorite, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.insertTourism(tourism)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
        }
    }

    override fun updateFavoriteTourism(tourism: ShowFavorite, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.update(tourism) }
    }

    override fun deleteFavoriteTourism(tourism: ShowFavorite, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.delete(tourism) }
    }
}

