package com.stackanswer.source.remote.response

import android.annotation.SuppressLint
import android.util.Log
import com.stackanswer.BuildConfig
import com.stackanswer.retrofit.ResultsItem
import com.stackanswer.retrofit.ResultsShowItem
import com.stackanswer.retrofit.detailmoviekt.DetailMovieResponse
import com.stackanswer.retrofit.detailshowkt.DetailShowResponse
import com.stackanswer.source.network.ApiResponse
import com.stackanswer.source.network.ApiService
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class RemoteDataSourceKt(private val apiService: ApiService) {
//    companion object {
//        @Volatile
//        private var instance: RemoteDataSourceKt? = null
//
//        fun getInstance(service: ApiService): RemoteDataSourceKt =
//            instance ?: synchronized(this) {
//                instance ?: RemoteDataSourceKt(service)
//            }
//    }

    @SuppressLint("CheckResult")
    fun getAllDetailShowPopular(id: String): Flowable<ApiResponse<DetailShowResponse>> {
        val resultData = PublishSubject.create<ApiResponse<DetailShowResponse>>()
//                .
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
//                .
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

//        client
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .take(1)
//                .subscribe({ response ->
//                    val dataArray = response.places
//                    resultData.onNext(if (dataArray.isNotEmpty()) ApiResponse.Success(dataArray) else ApiResponse.Empty)
//                }, { error ->
//                    resultData.onNext(ApiResponse.Error(error.message.toString()))
//                    Log.e("RemoteDataSource", error.toString())
//                })

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

//        client
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .take(1)
//                .subscribe({ response ->
//                    val dataArray = response.places
//                    resultData.onNext(if (dataArray.isNotEmpty()) ApiResponse.Success(dataArray) else ApiResponse.Empty)
//                }, { error ->
//                    resultData.onNext(ApiResponse.Error(error.message.toString()))
//                    Log.e("RemoteDataSource", error.toString())
//                })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
}


