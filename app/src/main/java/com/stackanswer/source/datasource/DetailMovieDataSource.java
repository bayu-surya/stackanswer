package com.stackanswer.source.datasource;

import com.stackanswer.viewmodel.DetailMovieViewModel;

public interface DetailMovieDataSource {

    void getAllDetailMovie(String language, String page, final DetailMovieViewModel.Callback arrayCallback);
}