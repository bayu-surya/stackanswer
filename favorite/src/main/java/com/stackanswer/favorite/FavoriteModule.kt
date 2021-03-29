package com.stackanswer.favorite

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { MovieFavoriteViewModelKt(get()) }
    viewModel { ShowFavoriteViewModelKt(get()) }
}