package com.stackanswer.tidakdigunakan;

import com.stackanswer.tidakdigunakan.DetailShowViewModel;

public interface DetailShowDataSource {

    void getAllDetailShow(String language, String page, final DetailShowViewModel.Callback arrayCallback);
}