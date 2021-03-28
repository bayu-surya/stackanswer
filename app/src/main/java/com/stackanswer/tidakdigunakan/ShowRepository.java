package com.stackanswer.tidakdigunakan;

import androidx.annotation.NonNull;

import com.stackanswer.model.Show;

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