package com.stackanswer.tidakdigunakan;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class DetailMovieViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile DetailMovieViewModelFactory INSTANCE;

    private final DetailMovieRepository mDetailMovieRepository;

    private DetailMovieViewModelFactory(DetailMovieRepository academyRepository) {
        mDetailMovieRepository = academyRepository;
    }

    public static DetailMovieViewModelFactory getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (DetailMovieViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DetailMovieViewModelFactory(Injection.provideDetailMovieRepository(context));
                }
            }
        }
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(DetailMovieViewModel.class)) {
            return (T) new DetailMovieViewModel(mDetailMovieRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}