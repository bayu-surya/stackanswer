package com.stackanswer.tidakdigunakan;

import androidx.annotation.NonNull;

import com.stackanswer.model.Movie;

public class DetailMovieRepository implements DetailMovieDataSource {

    private volatile static DetailMovieRepository INSTANCE = null;
    private final DetailMovieRemoteDataSource remoteDataSource;

    private DetailMovieRepository(@NonNull DetailMovieRemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public static DetailMovieRepository getInstance(DetailMovieRemoteDataSource remoteData) {
        if (INSTANCE == null) {
            synchronized (DetailMovieRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DetailMovieRepository(remoteData);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getAllDetailMovie(String language, String page, final DetailMovieViewModel.Callback arrayCallback) {
        remoteDataSource.getAllCourses(language, page, arrayCallback::sukses);
    }

    public interface Callback{
        void sukses(Movie movieList);
    }
}