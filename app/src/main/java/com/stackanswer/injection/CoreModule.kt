package com.stackanswer.injection

import androidx.room.Room
import com.stackanswer.core.BuildConfig
import com.stackanswer.core.domain.repository.*
import com.stackanswer.core.source.datasource.LocalDataSource
import com.stackanswer.core.source.datasource.LocalMovieFavoriteDataSource
import com.stackanswer.core.source.datasource.LocalShowDataSource
import com.stackanswer.core.source.datasource.LocalShowFavoriteDataSource
import com.stackanswer.core.source.local.room.movie.MovieDatabase
import com.stackanswer.core.source.local.room.moviefavorite.MovieFavoriteDatabase
import com.stackanswer.core.source.local.room.show.ShowDatabase
import com.stackanswer.core.source.local.room.showfavorite.ShowFavoriteDatabase
import com.stackanswer.core.source.network.ApiService
import com.stackanswer.core.source.remote.response.RemoteDataSourceKt
import com.stackanswer.core.source.repository.*
import com.stackanswer.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    factory { AppExecutors() }
    single { RemoteDataSourceKt(get()) }
    single { LocalDataSource(get()) }
    single { LocalShowDataSource(get()) }
    single { LocalShowFavoriteDataSource(get()) }
    single { LocalMovieFavoriteDataSource(get()) }
    single<IShowRepository> { ShowKtRepository(get(), get()) }
    single<IMovieRepository> { MovieKtRepository(get(), get()) }
    single<IDetailShowRepository> { DetailShowKtRepository(get(), get()) }
    single<IDetailMovieRepository> { DetailMovieKtRepository(get(), get()) }
    single<IShowFavoriteRepository> { ShowFavoriteKtRepository(get(), get()) }
    single<IMovieFavoriteRepository> { MovieFavoriteKtRepository(get(), get()) }
}

val movieDatabaseModule = module {
    factory { get<MovieDatabase>().tourismDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java, "movie2_database"
        ).fallbackToDestructiveMigration().build()
    }
}

val movieFavoriteDatabaseModule = module {
    factory { get<MovieFavoriteDatabase>().movieDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieFavoriteDatabase::class.java, "movie_database"
        ).fallbackToDestructiveMigration().build()
    }
}

val showDatabaseModule = module {
    factory { get<ShowDatabase>().tourismDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            ShowDatabase::class.java, "show2_database"
        ).fallbackToDestructiveMigration().build()
    }
}

val showFavoriteDatabaseModule = module {
    factory { get<ShowFavoriteDatabase>().showDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            ShowFavoriteDatabase::class.java, "show_database"
        ).fallbackToDestructiveMigration().build()
    }
}



//val showKtRepositoryModule = module {
//    factory { AppExecutors() }
//    single { LocalShowDataSource(get()) }
//    single { RemoteDataSourceKt(get()) }
//    single<IShowRepository> { ShowKtRepository(get(), get(), get()) }
//}

//val showFavoriteKtRepositoryModule = module {
//    factory { AppExecutors() }
//    single { LocalShowFavoriteDataSource(get()) }
//    single { RemoteDataSourceKt(get()) }
//    single<IShowFavoriteRepository> { ShowFavoriteKtRepository(get(), get(), get()) }
//}
//
//val movieKtRepositoryModule = module {
//    factory { AppExecutors() }
//    single { LocalDataSource(get()) }
//    single { RemoteDataSourceKt(get()) }
//    single<IMovieRepository> { MovieKtRepository(get(), get(), get()) }
//}
//
//val movieFavoriteKtRepositoryModule = module {
//    factory { AppExecutors() }
//    single { LocalMovieFavoriteDataSource(get()) }
//    single { RemoteDataSourceKt(get()) }
//    single<IMovieFavoriteRepository> { MovieFavoriteKtRepository(get(), get(), get()) }
//}
//
//val detailShowKtRepositoryModule = module {
//    factory { AppExecutors() }
//    single { LocalShowDataSource(get()) }
//    single { RemoteDataSourceKt(get()) }
//    single<IDetailShowRepository> { DetailShowKtRepository(get(), get(), get()) }
//}
//
//val detailMovieKtRepositoryModule = module {
//    factory { AppExecutors() }
//    single { LocalDataSource(get()) }
//    single { RemoteDataSourceKt(get()) }
//    single<IDetailMovieRepository> { DetailMovieKtRepository(get(), get(), get()) }
//}

