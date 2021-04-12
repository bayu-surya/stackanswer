package com.stackanswer.favorite.injection

import com.stackanswer.favorite.viewmodel.MovieFavoriteViewModelKt
import com.stackanswer.favorite.viewmodel.ShowFavoriteViewModelKt
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { MovieFavoriteViewModelKt(get()) }
    viewModel { ShowFavoriteViewModelKt(get()) }
}