package com.stackanswer.source.datasource;

import com.stackanswer.viewmodel.ShowViewModel;

public interface ShowDataSource {

    void getAllShow(String language, String page, final ShowViewModel.Callback arrayCallback);
}