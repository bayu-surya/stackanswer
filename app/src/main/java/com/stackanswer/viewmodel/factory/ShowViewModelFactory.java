package com.stackanswer.viewmodel.factory;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.stackanswer.source.repository.ShowRepository;
import com.stackanswer.injection.Injection;
import com.stackanswer.viewmodel.ShowViewModel;

public class ShowViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ShowViewModelFactory INSTANCE;

    private final ShowRepository mShowRepository;

    private ShowViewModelFactory(ShowRepository academyRepository) {
        mShowRepository = academyRepository;
    }

    public static ShowViewModelFactory getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (ShowViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ShowViewModelFactory(Injection.provideShowRepository(context));
                }
            }
        }
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(ShowViewModel.class)) {
            return (T) new ShowViewModel(mShowRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}