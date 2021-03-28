package com.stackanswer.injection

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    useCaseModule,
                    viewModelModule,
                    networkModule,
                    repositoryModule,
                    movieDatabaseModule,
                    showDatabaseModule,
                    movieFavoriteDatabaseModule,
                    showFavoriteDatabaseModule
//                    showKtRepositoryModule,
//                    movieKtRepositoryModule,
//                    showFavoriteKtRepositoryModule,
//                    movieFavoriteKtRepositoryModule,
//                    detailShowKtRepositoryModule,
//                    detailMovieKtRepositoryModule
                )
            )
        }
    }
}