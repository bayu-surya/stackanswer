package com.stackanswer.core.source.repository

import com.stackanswer.core.domain.repository.IMovieFavoriteRepository
import com.stackanswer.core.source.datasource.LocalMovieFavoriteDataSource
import com.stackanswer.core.source.local.room.moviefavorite.MovieFavorite
import com.stackanswer.core.utils.AppExecutors
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executor

class MovieFavoriteKtRepository(
    private val localDataSource: LocalMovieFavoriteDataSource,
    private val appExecutors: AppExecutors
) : IMovieFavoriteRepository {

    override fun getAllTourism(): Flowable<List<MovieFavorite>> {
        return localDataSource.getAllTourism()
    }

    override fun setFavoriteTourism(tourism: MovieFavorite, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.insertTourism(tourism)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        }
//        try {
//            appExecutors.diskIO().execute {
//                localDataSource.insertTourism(tourism)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe()
//            }
//        } catch (Ex e) {
//            System.out.println(e)
//        } finally{
//            appExecutors.diskIO().; // This is the method we use to avoid memory Leaks.
//            // eService.shutdownNow(); // -do-
//        }
    }

    override fun updateFavoriteTourism(tourism: MovieFavorite, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.update(tourism) }
    }

    override fun deleteFavoriteTourism(tourism: MovieFavorite, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.delete(tourism) }
    }
}
