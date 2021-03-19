package com.stackanswer.viewmodel.factory;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.stackanswer.source.repository.DetailShowRepository;
import com.stackanswer.injection.Injection;
import com.stackanswer.viewmodel.DetailShowViewModel;

public class DetailShowViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile DetailShowViewModelFactory INSTANCE;

    private final DetailShowRepository mDetailShowRepository;

    private DetailShowViewModelFactory(DetailShowRepository academyRepository) {
        mDetailShowRepository = academyRepository;
    }

    public static DetailShowViewModelFactory getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (DetailShowViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DetailShowViewModelFactory(Injection.provideDetailShowRepository(context));
                }
            }
        }
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(DetailShowViewModel.class)) {
            return (T) new DetailShowViewModel(mDetailShowRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}