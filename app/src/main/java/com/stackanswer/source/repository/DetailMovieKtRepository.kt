package com.stackanswer.source.repository

import com.stackanswer.core.domain.repository.IDetailMovieRepository
import com.stackanswer.retrofit.detailmoviekt.DetailMovieResponse
import com.stackanswer.source.NetworkBoundResource
import com.stackanswer.source.Resource
import com.stackanswer.source.datasource.LocalDataSource
import com.stackanswer.source.local.room.movie.MoviePopular
import com.stackanswer.source.network.ApiResponse
import com.stackanswer.source.remote.response.RemoteDataSourceKt
import com.stackanswer.utils.DataMapper
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailMovieKtRepository(
    private val remoteDataSource: RemoteDataSourceKt,
    private val localDataSource: LocalDataSource
//    ,
//    private val appExecutors: AppExecutors
) : IDetailMovieRepository {

//    companion object {
//        @Volatile
//        private var instance: DetailMovieKtRepository? = null
//
//        fun getInstance(
//            remoteData: RemoteDataSourceKt,
//            localData: LocalDataSource,
//            appExecutors: AppExecutors
//        ): DetailMovieKtRepository =
//            instance ?: synchronized(this) {
//                instance ?: DetailMovieKtRepository(remoteData,
//                        localData,
//                        appExecutors)
//            }
//    }

    override fun getAllTourism(id: String): Flowable<Resource<List<MoviePopular>>> =
        object : NetworkBoundResource<List<MoviePopular>, DetailMovieResponse>() {
            override fun loadFromDB(): Flowable<List<MoviePopular>> {
//                Flowable<List<MoviePopular>> a= localDataSource.getAllTourism().map { it };
//                return a
                return localDataSource.getAllTourism().map { it }
            }

            override fun shouldFetch(data: List<MoviePopular>?): Boolean =
//                data == null || data.isEmpty()
                true // ganti dengan true jika ingin selalu mengambil data dari internet

            override fun createCall(): Flowable<ApiResponse<DetailMovieResponse>> =
                remoteDataSource.getAllDetailMoviePopular(id)

            override fun saveCallResult(data: DetailMovieResponse) {
                val dataItem = DataMapper.mapDetailMovieResponsesToEntities(data)
                val tourismList = ArrayList<MoviePopular>()
                tourismList.add(dataItem)
                localDataSource.insertTourism(tourismList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()

//    override fun getFavoriteTourism(): Flowable<List<MoviePopular>> {
//        TODO("Not yet implemented")
//    }
//
//    override fun setFavoriteTourism(tourism: MoviePopular, state: Boolean) {
//        TODO("Not yet implemented")
//    }

//    override fun getFavoriteTourism(): Flowable<List<MoviePopular>> {
//        return localDataSource.getFavoriteTourism().map { DataMapper.mapEntitiesToDomain(it) }
//    }
//
//    override fun setFavoriteTourism(tourism: MoviePopular, state: Boolean) {
//        val tourismEntity = DataMapper.mapDomainToEntity(tourism)
//        appExecutors.diskIO().execute { localDataSource.setFavoriteTourism(tourismEntity, state) }
//    }
}

