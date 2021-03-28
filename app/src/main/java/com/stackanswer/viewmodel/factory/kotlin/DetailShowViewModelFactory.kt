package com.stackanswer.viewmodel.factory.kotlin

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stackanswer.core.domain.usecase.DetailShowUseCaseKt
import com.stackanswer.injection.kotlin.Injection
import com.stackanswer.viewmodel.DetailShowViewModelKt

class DetailShowViewModelFactory private constructor(private val tourismUseCase: DetailShowUseCaseKt) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: DetailShowViewModelFactory? = null

        fun getInstance(context: Context): DetailShowViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: DetailShowViewModelFactory(
                    Injection.provideDetailShowUseCase(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(DetailShowViewModelKt::class.java) -> {
                DetailShowViewModelKt(tourismUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}