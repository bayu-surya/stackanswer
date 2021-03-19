package com.stackanswer.source.remote.response;

import android.util.Log;

import com.stackanswer.source.datasource.JsonHelper;
import com.stackanswer.source.repository.DetailMovieRepository;
import com.stackanswer.model.Movie;

public class DetailMovieRemoteDataSource {

    private static DetailMovieRemoteDataSource INSTANCE;
    private final JsonHelper jsonHelper;

    private DetailMovieRemoteDataSource(JsonHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

    public static DetailMovieRemoteDataSource getInstance(JsonHelper helper) {
        if (INSTANCE == null) {
            INSTANCE = new DetailMovieRemoteDataSource(helper);
        }
        return INSTANCE;
    }

    public void getAllCourses(String language, String page, final DetailMovieRepository.Callback arrayCallback) {
        jsonHelper.loadDetailMovie(language, page, movieList2 -> {
            arrayCallback.sukses(movieList2);
            Log.d(TAG, "sukses: "+movieList2.toString());
        });
    }

    private static final String TAG = "MainViewModel";

    public interface CallbackDetailMovie{
        void sukses(Movie movieList);
    }

}
