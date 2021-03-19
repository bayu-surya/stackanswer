package com.stackanswer.injection;

import android.content.Context;

import com.stackanswer.source.datasource.JsonHelper;
import com.stackanswer.source.datasource.LocalMovieDataSource;
import com.stackanswer.source.datasource.LocalShowDataSource;
import com.stackanswer.source.local.room.moviefavorite.MovieFavoriteDatabase;
import com.stackanswer.source.local.room.showfavorite.ShowFavoriteDatabase;
import com.stackanswer.source.remote.response.DetailMovieRemoteDataSource;
import com.stackanswer.source.remote.response.DetailShowRemoteDataSource;
import com.stackanswer.source.remote.response.RemoteDataSource;
import com.stackanswer.source.remote.response.ShowRemoteDataSource;
import com.stackanswer.source.repository.DetailMovieRepository;
import com.stackanswer.source.repository.DetailShowRepository;
import com.stackanswer.source.repository.MovieFavoriteRepository;
import com.stackanswer.source.repository.MovieRepository;
import com.stackanswer.source.repository.ShowFavoriteRepository;
import com.stackanswer.source.repository.ShowRepository;

public class Injection {

    public static MovieRepository provideRepository(Context context) {
        RemoteDataSource remoteDataSource = RemoteDataSource.getInstance(new JsonHelper(context));
        return MovieRepository.getInstance(remoteDataSource);
    }

    public static ShowRepository provideShowRepository(Context context) {
        ShowRemoteDataSource remoteDataSource = ShowRemoteDataSource.getInstance(new JsonHelper(context));
        return ShowRepository.getInstance(remoteDataSource);
    }

    public static DetailShowRepository provideDetailShowRepository(Context context) {
        DetailShowRemoteDataSource remoteDataSource = DetailShowRemoteDataSource.getInstance(new JsonHelper(context));
        return DetailShowRepository.getInstance(remoteDataSource);
    }

    public static DetailMovieRepository provideDetailMovieRepository(Context context) {
        DetailMovieRemoteDataSource remoteDataSource = DetailMovieRemoteDataSource.getInstance(new JsonHelper(context));
        return DetailMovieRepository.getInstance(remoteDataSource);
    }

    public static MovieFavoriteRepository provideMovieFavriteRepository(Context context) {
        MovieFavoriteDatabase database = MovieFavoriteDatabase.getInstance(context);
        LocalMovieDataSource localDataSource = LocalMovieDataSource.getInstance(database.movieDao());
        return MovieFavoriteRepository.getInstance(localDataSource);
    }

    public static ShowFavoriteRepository provideShowFavriteRepository(Context context) {
        ShowFavoriteDatabase database = ShowFavoriteDatabase.getInstance(context);
        LocalShowDataSource localDataSource = LocalShowDataSource.getInstance(database.showDao());
        return ShowFavoriteRepository.getInstance(localDataSource);
    }
}