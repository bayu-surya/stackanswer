package com.stackanswer.viewmodel.factory.kotlin

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stackanswer.core.domain.usecase.ShowUseCaseKt
import com.stackanswer.injection.kotlin.Injection
import com.stackanswer.viewmodel.ShowViewModelKt

class ShowViewModelFactory private constructor(private val tourismUseCase: ShowUseCaseKt) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ShowViewModelFactory? = null

        fun getInstance(context: Context): ShowViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ShowViewModelFactory(
                    Injection.provideShowUseCase(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(ShowViewModelKt::class.java) -> {
                ShowViewModelKt(tourismUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}