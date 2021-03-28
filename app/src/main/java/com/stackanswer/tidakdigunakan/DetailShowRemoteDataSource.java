package com.stackanswer.tidakdigunakan;

import android.util.Log;

import com.stackanswer.model.Show;

public class DetailShowRemoteDataSource {

    private static DetailShowRemoteDataSource INSTANCE;
    private final JsonHelper jsonHelper;

    private DetailShowRemoteDataSource(JsonHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

    public static DetailShowRemoteDataSource getInstance(JsonHelper helper) {
        if (INSTANCE == null) {
            INSTANCE = new DetailShowRemoteDataSource(helper);
        }
        return INSTANCE;
    }

    public void getAllCourses(String language, String page, final DetailShowRepository.Callback arrayCallback) {
        jsonHelper.loadDetailShow(language, page, movieList2 -> {
            arrayCallback.sukses(movieList2);
            Log.d(TAG, "sukses: "+movieList2.toString());
        });
    }

    private static final String TAG = "MainViewModel";

    public interface CallbackDetailShow{
        void sukses(Show movieList);
    }

}
