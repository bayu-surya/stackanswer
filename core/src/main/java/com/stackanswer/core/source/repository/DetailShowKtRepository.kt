package com.stackanswer.core.source.repository

import com.stackanswer.core.domain.repository.IDetailShowRepository
import com.stackanswer.core.retrofit.detailshowkt.DetailShowResponse
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

class DetailShowKtRepository(
    private val remoteDataSource: RemoteDataSourceKt,
    private val localDataSource: LocalShowDataSource
) : IDetailShowRepository {

    override fun getAllTourism(id: String): Flowable<Resource<List<ShowPopular>>> =
        object : NetworkBoundResource<List<ShowPopular>, DetailShowResponse>() {
            override fun loadFromDB(): Flowable<List<ShowPopular>> {
                return localDataSource.getAllTourism().map { it }
            }

            override fun shouldFetch(data: List<ShowPopular>?): Boolean =
//                data == null || data.isEmpty()
                true // ganti dengan true jika ingin selalu mengambil data dari internet

            override fun createCall(): Flowable<ApiResponse<DetailShowResponse>> =
                remoteDataSource.getAllDetailShowPopular(id)

            override fun saveCallResult(data: DetailShowResponse) {
                val dataItem = DataMapper.mapDetailShowResponsesToEntities(data)
                val tourismList = ArrayList<ShowPopular>()
                tourismList.add(dataItem)
                localDataSource.insertTourism(tourismList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()
}

