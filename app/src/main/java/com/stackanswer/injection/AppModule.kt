package com.stackanswer.injection

import com.stackanswer.core.domain.usecase.*
import com.stackanswer.viewmodel.*
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCaseKt> { MovieInteractor(get()) }
    factory<ShowUseCaseKt> { ShowInteractor(get()) }
    factory<MovieFavoriteUseCaseKt> { MovieFavoriteInteractor(get()) }
    factory<ShowFavoriteUseCaseKt> { ShowFavoriteInteractor(get()) }
    factory<DetailMovieUseCaseKt> { DetailMovieInteractor(get()) }
    factory<DetailShowUseCaseKt> { DetailShowInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModelKt(get()) }
    viewModel { ShowViewModelKt(get()) }
    viewModel { MovieFavoriteViewModelKt(get()) }
    viewModel { ShowFavoriteViewModelKt(get()) }
    viewModel { DetailMovieViewModelKt(get()) }
    viewModel { DetailShowViewModelKt(get()) }
}