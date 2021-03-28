package com.stackanswer.viewmodel.factory.kotlin

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stackanswer.core.domain.usecase.MovieUseCaseKt
import com.stackanswer.injection.kotlin.Injection
import com.stackanswer.viewmodel.MovieViewModelKt

class ViewModelFactory private constructor(private val tourismUseCase: MovieUseCaseKt) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideTourismUseCase(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(MovieViewModelKt::class.java) -> {
                MovieViewModelKt(tourismUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}