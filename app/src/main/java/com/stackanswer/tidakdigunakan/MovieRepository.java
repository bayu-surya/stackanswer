package com.stackanswer.tidakdigunakan;

import android.util.Log;

import androidx.annotation.NonNull;

import com.stackanswer.model.Movie;

import java.util.List;

public class MovieRepository implements MovieDataSource {

    private volatile static MovieRepository INSTANCE = null;
    private final RemoteDataSource remoteDataSource;

    private MovieRepository(@NonNull RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public static MovieRepository getInstance(RemoteDataSource remoteData) {
        if (INSTANCE == null) {
            synchronized (MovieRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MovieRepository(remoteData);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getAllMovie(String language, String page, final MovieViewModel.Callback arrayCallback) {
        remoteDataSource.getAllCourses(language, page, movieList2 -> {
            arrayCallback.sukses(movieList2);
            Log.d(TAG, "loadMovie: 2 "+movieList2.size());
        });
    }

    private static final String TAG = "MainViewModel";
    public interface Callback{
        void sukses(List<Movie> movieList);
    }
}