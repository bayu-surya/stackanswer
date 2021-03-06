package com.stackanswer.core.source.repository

import android.util.Log
import com.stackanswer.core.domain.repository.IMovieRepository
import com.stackanswer.core.retrofit.ResultsItem
import com.stackanswer.core.source.NetworkBoundResource
import com.stackanswer.core.source.Resource
import com.stackanswer.core.source.datasource.LocalDataSource
import com.stackanswer.core.source.local.room.movie.MoviePopular
import com.stackanswer.core.source.network.ApiResponse
import com.stackanswer.core.source.remote.response.RemoteDataSourceKt
import com.stackanswer.core.utils.DataMapper
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieKtRepository(
    private val remoteDataSource: RemoteDataSourceKt,
    private val localDataSource: LocalDataSource
) : IMovieRepository {

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
}

