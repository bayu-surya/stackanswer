package com.stackanswer.source.network

import com.stackanswer.retrofit.ListMovieResponse
import com.stackanswer.retrofit.ListShowResponse
import com.stackanswer.retrofit.detailmoviekt.DetailMovieResponse
import com.stackanswer.retrofit.detailshowkt.DetailShowResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("3/movie/popular?")
    fun getListMoviePopuler(
            @Query("api_key") api_key: String?,
            @Query("language") language: String?,
            @Query("page") page: String?
    ): Flowable<ListMovieResponse?>?

    @GET("3/tv/popular?")
    fun getListShowPopuler(
            @Query("api_key") api_key: String?,
            @Query("language") language: String?,
            @Query("page") page: String?
    ): Flowable<ListShowResponse?>?

    @GET("3/tv/{id_movie}?")
    fun getDetailShowPopuler(
            @Path("id_movie") id_movie: String?,
            @Query("api_key") api_key: String?,
            @Query("language") language: String?
    ): Flowable<DetailShowResponse?>?

    @GET("3/movie/{id_movie}?")
    fun getDetailMoviePopuler(
            @Path("id_movie") id_movie: String?,
            @Query("api_key") api_key: String?,
            @Query("language") language: String?
    ): Flowable<DetailMovieResponse?>?
}
