package com.stackanswer.tidakdigunakan;

import android.content.Context;
import android.util.Log;

import com.stackanswer.BuildConfig;
import com.stackanswer.tidakdigunakan.DetailMovieRemoteDataSource;
import com.stackanswer.tidakdigunakan.DetailShowRemoteDataSource;
import com.stackanswer.tidakdigunakan.RemoteDataSource;
import com.stackanswer.tidakdigunakan.ShowRemoteDataSource;
import com.stackanswer.model.Movie;
import com.stackanswer.model.Show;
import com.stackanswer.tidakdigunakan.ApiConfig;
import com.stackanswer.tidakdigunakan.listMoviePopuler.ListMoviePopularResponse;
import com.stackanswer.tidakdigunakan.listMoviePopuler.ResultsItem;
import com.stackanswer.tidakdigunakan.listShowPopular.ListShowPopularResponse;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JsonHelper {

    private final Context context;

    public JsonHelper(Context context) {
        this.context = context;
    }

    private static final String TAG = "MainViewModel";
    private static final String API_KEY_MOVIEDB = BuildConfig.API_KEY;

    public void loadMovie(String language, String page, final RemoteDataSource.Callback arrayCallback){
        final List<Movie> movieList = new ArrayList<>();
        Call<ListMoviePopularResponse> client = ApiConfig.getApiService().getListMoviePopuler(API_KEY_MOVIEDB, language, page);
        client.enqueue(new Callback<ListMoviePopularResponse>() {
            @Override
            public void onResponse(@NotNull Call<ListMoviePopularResponse> call, @NotNull Response<ListMoviePopularResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<ResultsItem> resultsItem = response.body().getResults();
                        for (int i=0;i<resultsItem.size();i++){
                            Movie movie = new Movie();
                            movie.setId(resultsItem.get(i).getId());
                            movie.setOverview(resultsItem.get(i).getOverview());
                            movie.setOriginalLanguage(resultsItem.get(i).getOriginalLanguage());
                            movie.setOriginalTitle(resultsItem.get(i).getOriginalTitle());
                            movie.setVideo(resultsItem.get(i).isVideo());
                            movie.setTitle(resultsItem.get(i).getTitle());
                            movie.setGenreIds(resultsItem.get(i).getGenreIds());
                            movie.setPosterPath(resultsItem.get(i).getPosterPath());
                            movie.setBackdropPath(resultsItem.get(i).getBackdropPath());
                            movie.setReleaseDate(resultsItem.get(i).getReleaseDate());
                            movie.setPopularity(resultsItem.get(i).getPopularity());
                            movie.setVoteAverage(resultsItem.get(i).getVoteAverage());
                            movie.setAdult(resultsItem.get(i).isAdult());
                            movie.setVoteCount(resultsItem.get(i).getVoteCount());
                            movieList.add(movie);
                        }
                        Log.d(TAG, "loadMovie: 2 "+movieList.size());
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}");
                }
                arrayCallback.sukses(movieList);
            }
            @Override
            public void onFailure(@NotNull Call<ListMoviePopularResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                arrayCallback.sukses(movieList);
            }
        });
    }

    public void loadShow(String language, String page, final ShowRemoteDataSource.CallbackShow arrayCallback){
        final List<Show> showList = new ArrayList<>();
        Call<ListShowPopularResponse> client = ApiConfig.getApiService().getListShowPopuler(API_KEY_MOVIEDB, language, page);
        client.enqueue(new Callback<ListShowPopularResponse>() {
            @Override
            public void onResponse(@NotNull Call<ListShowPopularResponse> call, @NotNull Response<ListShowPopularResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<com.stackanswer.tidakdigunakan.listShowPopular.ResultsItem> resultsItem = response.body().getResults();
                        for (int i=0;i<resultsItem.size();i++){
                            Show show = new Show();
                            show.setId(resultsItem.get(i).getId());
                            show.setOverview(resultsItem.get(i).getOverview());
                            show.setOriginalLanguage(resultsItem.get(i).getOriginalLanguage());
                            show.setFirstAirDate(resultsItem.get(i).getFirstAirDate());
                            show.setOriginCountry(resultsItem.get(i).getOriginCountry());
                            show.setOriginalName(resultsItem.get(i).getOriginalName());
                            show.setGenreIds(resultsItem.get(i).getGenreIds());
                            show.setPosterPath(resultsItem.get(i).getPosterPath());
                            show.setBackdropPath(resultsItem.get(i).getBackdropPath());
                            show.setPopularity(resultsItem.get(i).getPopularity());
                            show.setVoteAverage(resultsItem.get(i).getVoteAverage());
                            show.setName(resultsItem.get(i).getName());
                            show.setVoteCount(resultsItem.get(i).getVoteCount());
                            showList.add(show);
                        }
                        Log.d(TAG, "loadMovie: 2 "+showList.size());
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}");
                }
                arrayCallback.sukses(showList);
            }
            @Override
            public void onFailure(@NotNull Call<ListShowPopularResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                arrayCallback.sukses(showList);
            }
        });
    }

    public void loadDetailShow(String language, String page, final DetailShowRemoteDataSource.CallbackDetailShow arrayCallback){
        final Show show = new Show();
        Call<com.stackanswer.tidakdigunakan.detailshow.Response> client = ApiConfig.getApiService().getDetailShowPopuler(page, API_KEY_MOVIEDB, language);
        client.enqueue(new Callback<com.stackanswer.tidakdigunakan.detailshow.Response>() {
            @Override
            public void onResponse(@NotNull Call<com.stackanswer.tidakdigunakan.detailshow.Response> call, @NotNull Response<com.stackanswer.tidakdigunakan.detailshow.Response> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        com.stackanswer.tidakdigunakan.detailshow.Response data = response.body();
                        show.setId(data.getId());
                        show.setOverview(data.getOverview());
                        show.setOriginalLanguage(data.getOriginalLanguage());
                        show.setFirstAirDate(data.getFirstAirDate());
                        show.setOriginCountry(data.getOriginCountry());
                        show.setOriginalName(data.getOriginalName());
                        show.setPosterPath(data.getPosterPath());
                        show.setBackdropPath(data.getBackdropPath());
                        show.setPopularity(data.getPopularity());
                        show.setVoteAverage(data.getVoteAverage());
                        show.setName(data.getName());
                        show.setVoteCount(data.getVoteCount());
                        List<String> list = new ArrayList<>();
                        for (int i=0;i<data.getGenres().size();i++) {
                            list.add(data.getGenres().get(i).getName());
                        }
                        show.setGenreIds(list);

                        Log.d(TAG, "loadDetailMovie: "+response.body().getName()+context);
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}");
                }
                arrayCallback.sukses(show);
            }
            @Override
            public void onFailure(@NotNull Call<com.stackanswer.tidakdigunakan.detailshow.Response> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                arrayCallback.sukses(show);
            }
        });
    }

    public void loadDetailMovie(String language, String page, final DetailMovieRemoteDataSource.CallbackDetailMovie arrayCallback){
        final Movie movie = new Movie();
        Call<com.stackanswer.tidakdigunakan.detailmovie.Response> client = ApiConfig.getApiService().getDetailMoviePopuler(page, API_KEY_MOVIEDB, language);
        client.enqueue(new Callback<com.stackanswer.tidakdigunakan.detailmovie.Response>() {
            @Override
            public void onResponse(@NotNull Call<com.stackanswer.tidakdigunakan.detailmovie.Response> call, @NotNull Response<com.stackanswer.tidakdigunakan.detailmovie.Response> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        com.stackanswer.tidakdigunakan.detailmovie.Response data = response.body();
                        movie.setId(data.getId());
                        movie.setOverview(data.getOverview());
                        movie.setOriginalLanguage(data.getOriginalLanguage());
                        movie.setOriginalTitle(data.getOriginalTitle());
                        movie.setVideo(data.isVideo());
                        movie.setTitle(data.getTitle());
                        movie.setPosterPath(data.getPosterPath());
                        movie.setBackdropPath(data.getBackdropPath());
                        movie.setReleaseDate(data.getReleaseDate());
                        movie.setPopularity(data.getPopularity());
                        movie.setVoteAverage(data.getVoteAverage());
                        movie.setAdult(data.isAdult());
                        movie.setVoteCount(data.getVoteCount());
                        List<String> list = new ArrayList<>();
                        for (int i=0;i<data.getGenres().size();i++) {
                            list.add(data.getGenres().get(i).getName());
                        }
                        movie.setGenreIds(list);
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}");
                }
                arrayCallback.sukses(movie);
            }
            @Override
            public void onFailure(@NotNull Call<com.stackanswer.tidakdigunakan.detailmovie.Response> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                arrayCallback.sukses(movie);
            }
        });
    }
}