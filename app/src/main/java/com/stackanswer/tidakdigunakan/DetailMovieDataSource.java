package com.stackanswer.tidakdigunakan;

import com.stackanswer.tidakdigunakan.DetailMovieViewModel;

public interface DetailMovieDataSource {

    void getAllDetailMovie(String language, String page, final DetailMovieViewModel.Callback arrayCallback);
}