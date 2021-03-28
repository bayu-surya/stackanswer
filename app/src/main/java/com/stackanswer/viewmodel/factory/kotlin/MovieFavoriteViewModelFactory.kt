package com.stackanswer.viewmodel.factory.kotlin

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stackanswer.core.domain.usecase.MovieFavoriteUseCaseKt
import com.stackanswer.injection.kotlin.Injection
import com.stackanswer.viewmodel.MovieFavoriteViewModelKt

class MovieFavoriteViewModelFactory private constructor(private val tourismUseCase: MovieFavoriteUseCaseKt) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: MovieFavoriteViewModelFactory? = null

        fun getInstance(context: Context): MovieFavoriteViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: MovieFavoriteViewModelFactory(
                    Injection.provideMovieFavoriteUseCase(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(MovieFavoriteViewModelKt::class.java) -> {
                MovieFavoriteViewModelKt(tourismUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}