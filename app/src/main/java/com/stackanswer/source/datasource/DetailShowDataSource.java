package com.stackanswer.source.datasource;

import com.stackanswer.viewmodel.DetailShowViewModel;

public interface DetailShowDataSource {

    void getAllDetailShow(String language, String page, final DetailShowViewModel.Callback arrayCallback);
}