package com.stackanswer.core.source.repository

import com.stackanswer.core.domain.repository.IShowRepository
import com.stackanswer.core.retrofit.ResultsShowItem
import com.stackanswer.core.source.NetworkBoundResource
import com.stackanswer.core.source.Resource
import com.stackanswer.core.source.datasource.LocalShowDataSource
import com.stackanswer.core.source.local.room.show.ShowPopular
import com.stackanswer.core.source.network.ApiResponse
import com.stackanswer.core.source.remote.response.RemoteDataSourceKt
import com.stackanswer.core.utils.DataMapper
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ShowKtRepository(
    private val remoteDataSource: RemoteDataSourceKt,
    private val localDataSource: LocalShowDataSource
) : IShowRepository {

    override fun getAllTourism(): Flowable<Resource<List<ShowPopular>>> =
        object : NetworkBoundResource<List<ShowPopular>, List<ResultsShowItem>>() {
            override fun loadFromDB(): Flowable<List<ShowPopular>> {
                return localDataSource.getAllTourism().map { it }
            }

            override fun shouldFetch(data: List<ShowPopular>?): Boolean =
//                data == null || data.isEmpty()
                true // ganti dengan true jika ingin selalu mengambil data dari internet

            override fun createCall(): Flowable<ApiResponse<List<ResultsShowItem>>> =
                remoteDataSource.getAllShowPopular()

            override fun saveCallResult(data: List<ResultsShowItem>) {
                val tourismList = DataMapper.mapShowResponsesToEntities(data)
                localDataSource.insertTourism(tourismList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()
}

