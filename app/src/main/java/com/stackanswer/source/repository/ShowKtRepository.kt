package com.stackanswer.source.repository

import com.stackanswer.core.domain.repository.IShowRepository
import com.stackanswer.retrofit.ResultsShowItem
import com.stackanswer.source.NetworkBoundResource
import com.stackanswer.source.Resource
import com.stackanswer.source.datasource.kotlin.LocalShowDataSource
import com.stackanswer.source.local.room.show.ShowPopular
import com.stackanswer.source.network.ApiResponse
import com.stackanswer.source.remote.response.RemoteDataSourceKt
import com.stackanswer.utils.kotlin.AppExecutors
import com.stackanswer.utils.kotlin.DataMapper
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ShowKtRepository private constructor(
        private val remoteDataSource: RemoteDataSourceKt,
        private val localDataSource: LocalShowDataSource,
        private val appExecutors: AppExecutors
) : IShowRepository {

    companion object {
        @Volatile
        private var instance: ShowKtRepository? = null

        fun getInstance(
                remoteData: RemoteDataSourceKt,
                localData: LocalShowDataSource,
                appExecutors: AppExecutors
        ): ShowKtRepository =
            instance ?: synchronized(this) {
                instance ?: ShowKtRepository(remoteData,
                        localData,
                        appExecutors)
            }
    }

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

    override fun getFavoriteTourism(): Flowable<List<ShowPopular>> {
        TODO("Not yet implemented")
    }

    override fun setFavoriteTourism(tourism: ShowPopular, state: Boolean) {
        TODO("Not yet implemented")
    }

//    override fun getFavoriteTourism(): Flowable<List<ShowPopular>> {
//        return localDataSource.getFavoriteTourism().map { DataMapper.mapEntitiesToDomain(it) }
//    }
//
//    override fun setFavoriteTourism(tourism: ShowPopular, state: Boolean) {
//        val tourismEntity = DataMapper.mapDomainToEntity(tourism)
//        appExecutors.diskIO().execute { localDataSource.setFavoriteTourism(tourismEntity, state) }
//    }
}

