package com.stackanswer.tidakdigunakan;

import android.content.Context;

public class Injection {

//    public static MovieRepositoryKt provideRepository3(Context context) {
//        RemoteDataSourceKt remoteDataSource = RemoteDataSourceKt.getInstance(new JsonHelperKt(context));
//        return MovieRepositoryKt.getInstance(remoteDataSource);
//    }

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