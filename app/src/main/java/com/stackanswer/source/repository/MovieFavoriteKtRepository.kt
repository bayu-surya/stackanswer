package com.stackanswer.source.repository

import com.stackanswer.core.domain.repository.IMovieFavoriteRepository
import com.stackanswer.source.datasource.LocalMovieFavoriteDataSource
import com.stackanswer.source.local.room.moviefavorite.MovieFavorite
import com.stackanswer.source.remote.response.RemoteDataSourceKt
import com.stackanswer.utils.AppExecutors
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieFavoriteKtRepository(
    private val remoteDataSource: RemoteDataSourceKt,
    private val localDataSource: LocalMovieFavoriteDataSource,
    private val appExecutors: AppExecutors
) : IMovieFavoriteRepository {

//    companion object {
//        @Volatile
//        private var instance: MovieFavoriteKtRepository? = null
//
//        fun getInstance(
//            remoteData: RemoteDataSourceKt,
//            localData: LocalMovieFavoriteDataSource,
//            appExecutors: AppExecutors
//        ): MovieFavoriteKtRepository =
//            instance ?: synchronized(this) {
//                instance ?: MovieFavoriteKtRepository(remoteData,
//                        localData,
//                        appExecutors)
//            }
//    }

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
    }

    override fun updateFavoriteTourism(tourism: MovieFavorite, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.update(tourism) }
    }

    override fun deleteFavoriteTourism(tourism: MovieFavorite, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.delete(tourism) }
    }

    override fun deleteAllFavoriteTourism() {
        appExecutors.diskIO().execute { localDataSource.deleteAllMovie() }
    }

//    override fun getAllTourism(): Flowable<Resource<List<ShowPopular>>> =
//        object : NetworkBoundResource<List<ShowPopular>, List<ResultsShowItem>>() {
//            override fun loadFromDB(): Flowable<List<ShowPopular>> {
//                return localDataSource.getAllTourism().map { it }
//            }
//
//            override fun shouldFetch(data: List<ShowPopular>?): Boolean =
////                data == null || data.isEmpty()
//                true // ganti dengan true jika ingin selalu mengambil data dari internet
//
//            override fun createCall(): Flowable<ApiResponse<List<ResultsShowItem>>> =
//                remoteDataSource.getAllShowPopular()
//
//            override fun saveCallResult(data: List<ResultsShowItem>) {
//                val tourismList = DataMapper.mapShowResponsesToEntities(data)
//                localDataSource.insertTourism(tourismList)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe()
//            }
//        }.asFlowable()

//    override fun getFavoriteTourism(): Flowable<List<ShowPopular>> {
//        return localDataSource.getFavoriteTourism().map { DataMapper.mapEntitiesToDomain(it) }
//    }
//
//    override fun setFavoriteTourism(tourism: ShowPopular, state: Boolean) {
//        val tourismEntity = DataMapper.mapDomainToEntity(tourism)
//        appExecutors.diskIO().execute { localDataSource.setFavoriteTourism(tourismEntity, state) }
//    }
}

