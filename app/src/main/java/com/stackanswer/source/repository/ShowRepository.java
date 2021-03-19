package com.stackanswer.source.repository;

import androidx.annotation.NonNull;

import com.stackanswer.source.datasource.ShowDataSource;
import com.stackanswer.source.remote.response.ShowRemoteDataSource;
import com.stackanswer.model.Show;
import com.stackanswer.viewmodel.ShowViewModel;

import java.util.List;

public class ShowRepository implements ShowDataSource {

    private volatile static ShowRepository INSTANCE = null;
    private final ShowRemoteDataSource remoteDataSource;

    private ShowRepository(@NonNull ShowRemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public static ShowRepository getInstance(ShowRemoteDataSource remoteData) {
        if (INSTANCE == null) {
            synchronized (ShowRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ShowRepository(remoteData);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getAllShow(String language, String page, final ShowViewModel.Callback arrayCallback) {
        remoteDataSource.getAllCourses(language, page, arrayCallback::sukses);
    }

    public interface Callback{
        void sukses(List<Show> movieList);
    }
}