package com.stackanswer.tidakdigunakan

import android.annotation.SuppressLint
import android.util.Log
import com.stackanswer.BuildConfig
import com.stackanswer.retrofit.ResultsShowItem
import com.stackanswer.source.network.ApiResponse
import com.stackanswer.source.network.ApiService
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class ShowRemoteDataSourceKt private constructor(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: ShowRemoteDataSourceKt? = null

        fun getInstance(service: ApiService): ShowRemoteDataSourceKt =
            instance ?: synchronized(this) {
                instance ?: ShowRemoteDataSourceKt(service)
            }
    }

    @SuppressLint("CheckResult")
    fun getAllTourism(): Flowable<ApiResponse<List<ResultsShowItem>>> {
        val resultData = PublishSubject.create<ApiResponse<List<ResultsShowItem>>>()

        //get data from remote api
        val client = apiService.getListShowPopuler(BuildConfig.API_KEY, "en-US", "1");

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
}


