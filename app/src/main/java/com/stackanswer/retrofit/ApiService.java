package com.stackanswer.retrofit;

import com.stackanswer.retrofit.detailshow.Response;
import com.stackanswer.retrofit.listMoviePopuler.ListMoviePopularResponse;
import com.stackanswer.retrofit.listShowPopular.ListShowPopularResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("3/movie/popular?")
    Call<ListMoviePopularResponse> getListMoviePopuler(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") String page
    );

    @GET("3/tv/popular?")
    Call<ListShowPopularResponse> getListShowPopuler(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") String page
    );

    @GET("3/tv/{id_movie}?")
    Call<Response> getDetailShowPopuler(
            @Path("id_movie") String id_movie,
            @Query("api_key") String api_key,
            @Query("language") String language
    );

    @GET("3/movie/{id_movie}?")
    Call<com.stackanswer.retrofit.detailmovie.Response> getDetailMoviePopuler(
            @Path("id_movie") String id_movie,
            @Query("api_key") String api_key,
            @Query("language") String language
    );
}