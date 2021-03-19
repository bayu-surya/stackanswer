package com.stackanswer.source.remote.response;

import android.util.Log;

import com.stackanswer.source.datasource.JsonHelper;
import com.stackanswer.source.repository.ShowRepository;
import com.stackanswer.model.Show;

import java.util.List;

public class ShowRemoteDataSource {

    private static ShowRemoteDataSource INSTANCE;
    private final JsonHelper jsonHelper;

    private ShowRemoteDataSource(JsonHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

    public static ShowRemoteDataSource getInstance(JsonHelper helper) {
        if (INSTANCE == null) {
            INSTANCE = new ShowRemoteDataSource(helper);
        }
        return INSTANCE;
    }

    public void getAllCourses(String language, String page, final ShowRepository.Callback arrayCallback) {
        jsonHelper.loadShow(language, page, movieList2 -> {
            arrayCallback.sukses(movieList2);
            Log.d(TAG, "loadShow: 2 "+movieList2.size());
        });
    }

    private static final String TAG = "MainViewModel";

    public interface CallbackShow{
        void sukses(List<Show> movieList);
    }

}
