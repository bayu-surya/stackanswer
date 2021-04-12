package com.stackanswer.core.source.repository

import com.stackanswer.core.domain.repository.IDetailMovieRepository
import com.stackanswer.core.retrofit.detailmoviekt.DetailMovieResponse
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

class DetailMovieKtRepository(
    private val remoteDataSource: RemoteDataSourceKt,
    private val localDataSource: LocalDataSource
) : IDetailMovieRepository {

    override fun getAllTourism(id: String): Flowable<Resource<List<MoviePopular>>> =
        object : NetworkBoundResource<List<MoviePopular>, DetailMovieResponse>() {
            override fun loadFromDB(): Flowable<List<MoviePopular>> {
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
}

