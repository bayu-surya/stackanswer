package com.stackanswer.source.datasource;

import com.stackanswer.viewmodel.MovieViewModel;

public interface MovieDataSource {

    void getAllMovie(String language, String page, final MovieViewModel.Callback arrayCallback);
}