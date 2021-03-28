package com.stackanswer.tidakdigunakan;

public interface MovieDataSource {

    void getAllMovie(String language, String page, final MovieViewModel.Callback arrayCallback);
}