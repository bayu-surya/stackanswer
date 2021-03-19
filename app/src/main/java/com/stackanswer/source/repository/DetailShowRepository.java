package com.stackanswer.source.repository;

import androidx.annotation.NonNull;

import com.stackanswer.source.datasource.DetailShowDataSource;
import com.stackanswer.source.remote.response.DetailShowRemoteDataSource;
import com.stackanswer.model.Show;
import com.stackanswer.viewmodel.DetailShowViewModel;

public class DetailShowRepository implements DetailShowDataSource {

    private volatile static DetailShowRepository INSTANCE = null;
    private final DetailShowRemoteDataSource remoteDataSource;

    private DetailShowRepository(@NonNull DetailShowRemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public static DetailShowRepository getInstance(DetailShowRemoteDataSource remoteData) {
        if (INSTANCE == null) {
            synchronized (DetailShowRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DetailShowRepository(remoteData);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getAllDetailShow(String language, String page, final DetailShowViewModel.Callback arrayCallback) {
        remoteDataSource.getAllCourses(language, page, arrayCallback::sukses);
    }

    public interface Callback{
        void sukses(Show movieList);
    }
}