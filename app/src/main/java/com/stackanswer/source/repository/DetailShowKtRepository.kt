package com.stackanswer.source.repository

import com.stackanswer.core.domain.repository.IDetailShowRepository
import com.stackanswer.retrofit.detailshowkt.DetailShowResponse
import com.stackanswer.source.NetworkBoundResource
import com.stackanswer.source.Resource
import com.stackanswer.source.datasource.LocalShowDataSource
import com.stackanswer.source.local.room.show.ShowPopular
import com.stackanswer.source.network.ApiResponse
import com.stackanswer.source.remote.response.RemoteDataSourceKt
import com.stackanswer.utils.AppExecutors
import com.stackanswer.utils.DataMapper
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailShowKtRepository(
    private val remoteDataSource: RemoteDataSourceKt,
    private val localDataSource: LocalShowDataSource,
    private val appExecutors: AppExecutors
) : IDetailShowRepository {

//    companion object {
//        @Volatile
//        private var instance: DetailShowKtRepository? = null
//
//        fun getInstance(
//            remoteData: RemoteDataSourceKt,
//            localData: LocalShowDataSource,
//            appExecutors: AppExecutors
//        ): DetailShowKtRepository =
//            instance ?: synchronized(this) {
//                instance ?: DetailShowKtRepository(remoteData,
//                        localData,
//                        appExecutors)
//            }
//    }

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

