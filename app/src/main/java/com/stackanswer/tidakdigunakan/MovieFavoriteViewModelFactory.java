package com.stackanswer.tidakdigunakan;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MovieFavoriteViewModelFactory  extends ViewModelProvider.NewInstanceFactory {

    private static volatile MovieFavoriteViewModelFactory INSTANCE;
    private final MovieFavoriteRepository mApplication;

    private MovieFavoriteViewModelFactory(MovieFavoriteRepository application) {
        mApplication = application;
    }

    public static MovieFavoriteViewModelFactory getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (MovieFavoriteViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MovieFavoriteViewModelFactory(Injection.provideMovieFavriteRepository(context));
                }
            }
        }
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MovieFavoriteViewModel.class)) {
            return (T) new MovieFavoriteViewModel(mApplication);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}