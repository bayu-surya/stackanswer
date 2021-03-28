//package com.stackanswer.viewmodel.factory
//
//import android.content.Context
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.stackanswer.core.domain.usecase.DetailMovieUseCaseKt
//import com.stackanswer.injection.Injection
//import com.stackanswer.viewmodel.DetailMovieViewModelKt
//
//class DetailMovieViewModelFactory private constructor(private val tourismUseCase: DetailMovieUseCaseKt) :
//    ViewModelProvider.NewInstanceFactory() {
//
//    companion object {
//        @Volatile
//        private var instance: DetailMovieViewModelFactory? = null
//
//        fun getInstance(context: Context): DetailMovieViewModelFactory =
//            instance ?: synchronized(this) {
//                instance ?: DetailMovieViewModelFactory(
//                    Injection.provideDetailMovieUseCase(context)
//                )
//            }
//    }
//
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T =
//        when {
//            modelClass.isAssignableFrom(DetailMovieViewModelKt::class.java) -> {
//                DetailMovieViewModelKt(tourismUseCase) as T
//            }
//            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
//        }
//}