package com.stackanswer.core.source.remote.response

import android.annotation.SuppressLint
import android.util.Log
import com.stackanswer.core.BuildConfig
import com.stackanswer.core.retrofit.ResultsItem
import com.stackanswer.core.retrofit.ResultsShowItem
import com.stackanswer.core.retrofit.detailmoviekt.DetailMovieResponse
import com.stackanswer.core.retrofit.detailshowkt.DetailShowResponse
import com.stackanswer.core.source.network.ApiResponse
import com.stackanswer.core.source.network.ApiService
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class RemoteDataSourceKt(private val apiService: ApiService) {

    @SuppressLint("CheckResult")
    fun getAllDetailShowPopular(id: String): Flowable<ApiResponse<DetailShowResponse>> {
        val resultData = PublishSubject.create<ApiResponse<DetailShowResponse>>()
        //get data from remote api
        val client = apiService.getDetailShowPopuler(id, BuildConfig.API_KEY, "en-US" )

        client?.
        subscribeOn(Schedulers.computation())?.
        observeOn(AndroidSchedulers.mainThread())?.
        take(1)?.
        subscribe({ response ->
            resultData.onNext(
                    (if (response !=null)
                        ApiResponse.Success(response)
                    else
                        ApiResponse.Empty)
            )
        }, { error ->
            resultData.onNext(ApiResponse.Error(error.message.toString()))
            Log.e("RemoteDataSource", error.toString())
        })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun getAllDetailMoviePopular(id: String): Flowable<ApiResponse<DetailMovieResponse>> {
        val resultData = PublishSubject.create<ApiResponse<DetailMovieResponse>>()

        //get data from remote api
        val client = apiService.getDetailMoviePopuler(id, BuildConfig.API_KEY, "en-US")

        client?.
        subscribeOn(Schedulers.computation())?.
        observeOn(AndroidSchedulers.mainThread())?.
        take(1)?.
        subscribe({ response ->
            resultData.onNext(
                    (if (response !=null)
                        ApiResponse.Success(response)
                    else
                        ApiResponse.Empty)
            )
        }, { error ->
            resultData.onNext(ApiResponse.Error(error.message.toString()))
            Log.e("RemoteDataSource", error.toString())
        })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun getAllShowPopular(): Flowable<ApiResponse<List<ResultsShowItem>>> {
        val resultData = PublishSubject.create<ApiResponse<List<ResultsShowItem>>>()

        //get data from remote api
        val client = apiService.getListShowPopuler(BuildConfig.API_KEY, "en-US", "1")

        client?.
        subscribeOn(Schedulers.computation())?.
        observeOn(AndroidSchedulers.mainThread())?.
        take(1)?.
        subscribe({ response ->
            val dataArray = response?.results
            resultData.onNext(
                    (if (dataArray?.isNotEmpty() == true)
                        ApiResponse.Success(dataArray)
                    else
                        ApiResponse.Empty) as ApiResponse<List<ResultsShowItem>>
            )
        }, { error ->
            resultData.onNext(ApiResponse.Error(error.message.toString()))
            Log.e("RemoteDataSource", error.toString())
        })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun getAllTourism(): Flowable<ApiResponse<List<ResultsItem>>> {
        val resultData = PublishSubject.create<ApiResponse<List<ResultsItem>>>()

        //get data from remote api
        val client = apiService.getListMoviePopuler(BuildConfig.API_KEY, "en-US", "1")

        client?.
        subscribeOn(Schedulers.computation())?.
        observeOn(AndroidSchedulers.mainThread())?.
        take(1)?.
        subscribe({ response ->
            val dataArray = response?.results
            resultData.onNext(
                    (if (dataArray?.isNotEmpty() == true)
                        ApiResponse.Success(dataArray)
                    else
                        ApiResponse.Empty) as ApiResponse<List<ResultsItem>>
            )
        }, { error ->
            resultData.onNext(ApiResponse.Error(error.message.toString()))
            Log.e("RemoteDataSource", error.toString())
        })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
}


