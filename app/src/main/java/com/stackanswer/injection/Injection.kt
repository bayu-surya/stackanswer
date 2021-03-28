package com.stackanswer.injection

import android.content.Context
import com.stackanswer.core.domain.repository.*
import com.stackanswer.core.domain.usecase.*
import com.stackanswer.source.datasource.LocalMovieFavoriteDataSource
import com.stackanswer.source.datasource.LocalShowDataSource
import com.stackanswer.source.datasource.LocalShowFavoriteDataSource
import com.stackanswer.source.local.room.moviefavorite.MovieFavoriteDatabase
import com.stackanswer.source.local.room.show.ShowDatabase
import com.stackanswer.source.local.room.showfavorite.ShowFavoriteDatabase
import com.stackanswer.source.network.ApiConfig
import com.stackanswer.source.remote.response.RemoteDataSourceKt
import com.stackanswer.source.repository.*
import com.stackanswer.utils.AppExecutors

object Injection {

//    private fun provideRepository(context: Context): IMovieRepository {
//        val database = MovieDatabase.getInstance(context)
//
//        val remoteDataSource = RemoteDataSourceKt.getInstance(ApiConfig.provideApiService())
//        val localDataSource = LocalDataSource.getInstance(database.tourismDao())
//        val appExecutors = AppExecutors()
//
//        return MovieKtRepository.getInstance(
//                remoteDataSource,
//                localDataSource,
//                appExecutors
//        )
//    }
//
//    fun provideTourismUseCase(context: Context): MovieUseCaseKt {
//        val repository = provideRepository(context)
//        return MovieInteractor(repository)
//    }

//    private fun provideShowRepository(context: Context): IShowRepository {
//        val database = ShowDatabase.getInstance(context)
//
//        val remoteDataSource = RemoteDataSourceKt.getInstance(ApiConfig.provideApiService())
//        val localDataSource = LocalShowDataSource.getInstance(database.tourismDao())
//        val appExecutors = AppExecutors()
//
//        return ShowKtRepository.getInstance(
//                remoteDataSource,
//                localDataSource,
//                appExecutors
//        )
//    }
//
//    fun provideShowUseCase(context: Context): ShowUseCaseKt {
//        val repository = provideShowRepository(context)
//        return ShowInteractor(repository)
//    }

//    private fun provideDetailMovieRepository(context: Context): IDetailMovieRepository {
//        val database = MovieDatabase.getInstance(context)
//
//        val remoteDataSource = RemoteDataSourceKt.getInstance(ApiConfig.provideApiService())
//        val localDataSource = LocalDataSource.getInstance(database.tourismDao())
//        val appExecutors = AppExecutors()
//
//        return DetailMovieKtRepository.getInstance(
//                remoteDataSource,
//                localDataSource,
//                appExecutors
//        )
//    }
//
//    fun provideDetailMovieUseCase(context: Context): DetailMovieUseCaseKt {
//        val repository = provideDetailMovieRepository(context)
//        return DetailMovieInteractor(repository)
//    }

//    private fun provideDetailShowRepository(context: Context): IDetailShowRepository {
//        val database = ShowDatabase.getInstance(context)
//
//        val remoteDataSource = RemoteDataSourceKt.getInstance(ApiConfig.provideApiService())
//        val localDataSource = LocalShowDataSource.getInstance(database.tourismDao())
//        val appExecutors = AppExecutors()
//
//        return DetailShowKtRepository.getInstance(
//                remoteDataSource,
//                localDataSource,
//                appExecutors
//        )
//    }
//
//    fun provideDetailShowUseCase(context: Context): DetailShowUseCaseKt {
//        val repository = provideDetailShowRepository(context)
//        return DetailShowInteractor(repository)
//    }
//
//    private fun provideMovieFavoriteRepository(context: Context): IMovieFavoriteRepository {
//        val database = MovieFavoriteDatabase.getInstance(context)
//
//        val remoteDataSource = RemoteDataSourceKt.getInstance(ApiConfig.provideApiService())
//        val localDataSource = LocalMovieFavoriteDataSource.getInstance(database.movieDao())
//        val appExecutors = AppExecutors()
//
//        return MovieFavoriteKtRepository.getInstance(
//                remoteDataSource,
//                localDataSource,
//                appExecutors
//        )
//    }
//
//    fun provideMovieFavoriteUseCase(context: Context): MovieFavoriteUseCaseKt {
//        val repository = provideMovieFavoriteRepository(context)
//        return MovieFavoriteInteractor(repository)
//    }
//
//    private fun provideShowFavoriteRepository(context: Context): IShowFavoriteRepository {
//        val database = ShowFavoriteDatabase.getInstance(context)
//
//        val remoteDataSource = RemoteDataSourceKt.getInstance(ApiConfig.provideApiService())
//        val localDataSource = LocalShowFavoriteDataSource.getInstance(database.showDao())
//        val appExecutors = AppExecutors()
//
//        return ShowFavoriteKtRepository.getInstance(
//                remoteDataSource,
//                localDataSource,
//                appExecutors
//        )
//    }
//
//    fun provideShowFavoriteUseCase(context: Context): ShowFavoriteUseCaseKt {
//        val repository = provideShowFavoriteRepository(context)
//        return ShowFavoriteInteractor(repository)
//    }
}
