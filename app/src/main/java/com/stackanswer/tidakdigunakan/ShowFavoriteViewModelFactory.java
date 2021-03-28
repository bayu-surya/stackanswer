package com.stackanswer.tidakdigunakan;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ShowFavoriteViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private static volatile ShowFavoriteViewModelFactory INSTANCE;
    private final ShowFavoriteRepository mApplication;

    private ShowFavoriteViewModelFactory(ShowFavoriteRepository application) {
        mApplication = application;
    }

    public static ShowFavoriteViewModelFactory getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (ShowFavoriteViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ShowFavoriteViewModelFactory(Injection.provideShowFavriteRepository(context));
                }
            }
        }
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ShowFavoriteViewModel.class)) {
            return (T) new ShowFavoriteViewModel(mApplication);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}