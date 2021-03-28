package com.stackanswer.viewmodel.factory.kotlin

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stackanswer.core.domain.usecase.ShowFavoriteUseCaseKt
import com.stackanswer.injection.kotlin.Injection
import com.stackanswer.viewmodel.ShowFavoriteViewModelKt

class ShowFavoriteViewModelFactory private constructor(private val tourismUseCase: ShowFavoriteUseCaseKt) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ShowFavoriteViewModelFactory? = null

        fun getInstance(context: Context): ShowFavoriteViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ShowFavoriteViewModelFactory(
                    Injection.provideShowFavoriteUseCase(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(ShowFavoriteViewModelKt::class.java) -> {
                ShowFavoriteViewModelKt(tourismUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}