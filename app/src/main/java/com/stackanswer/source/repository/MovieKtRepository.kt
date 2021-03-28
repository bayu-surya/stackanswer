package com.stackanswer.source.repository

import android.util.Log
import com.stackanswer.core.domain.repository.IMovieRepository
import com.stackanswer.retrofit.ResultsItem
import com.stackanswer.source.NetworkBoundResource
import com.stackanswer.source.Resource
import com.stackanswer.source.datasource.kotlin.LocalDataSource
import com.stackanswer.source.local.room.movie.MoviePopular
import com.stackanswer.source.network.ApiResponse
import com.stackanswer.source.remote.response.RemoteDataSourceKt
import com.stackanswer.utils.kotlin.AppExecutors
import com.stackanswer.utils.kotlin.DataMapper
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieKtRepository private constructor(
        private val remoteDataSource: RemoteDataSourceKt,
        private val localDataSource: LocalDataSource,
        private val appExecutors: AppExecutors
) : IMovieRepository {

    companion object {
        @Volatile
        private var instance: MovieKtRepository? = null

        fun getInstance(
                remoteData: RemoteDataSourceKt,
                localData: LocalDataSource,
                appExecutors: AppExecutors
        ): MovieKtRepository =
            instance ?: synchronized(this) {
                instance ?: MovieKtRepository(remoteData,
                        localData,
                        appExecutors)
            }
    }

    override fun getAllTourism(): Flowable<Resource<List<MoviePopular>>> =
        object : NetworkBoundResource<List<MoviePopular>, List<ResultsItem>>() {
            override fun loadFromDB(): Flowable<List<MoviePopular>> {
                Log.d("TAG", "loadFromDB: 1")
                return localDataSource.getAllTourism().map { it }
            }

            override fun shouldFetch(data: List<MoviePopular>?): Boolean {
                Log.d("TAG", "loadFromDB: 2")
//                data == null || data.isEmpty()
                return true // ganti dengan true jika ingin selalu mengambil data dari internet
            }

            override fun createCall(): Flowable<ApiResponse<List<ResultsItem>>> {
                Log.d("TAG", "loadFromDB: 3")
                return remoteDataSource.getAllTourism()
            }

            override fun saveCallResult(data: List<ResultsItem>) {
                Log.d("TAG", "loadFromDB: 4")
                val tourismList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertTourism(tourismList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()

    override fun getFavoriteTourism(): Flowable<List<MoviePopular>> {
        TODO("Not yet implemented")
    }

    override fun setFavoriteTourism(tourism: MoviePopular, state: Boolean) {
        TODO("Not yet implemented")
    }

//    override fun getFavoriteTourism(): Flowable<List<MoviePopular>> {
//        return localDataSource.getFavoriteTourism().map { DataMapper.mapEntitiesToDomain(it) }
//    }
//
//    override fun setFavoriteTourism(tourism: MoviePopular, state: Boolean) {
//        val tourismEntity = DataMapper.mapDomainToEntity(tourism)
//        appExecutors.diskIO().execute { localDataSource.setFavoriteTourism(tourismEntity, state) }
//    }
}

