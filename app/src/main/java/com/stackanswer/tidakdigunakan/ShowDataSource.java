package com.stackanswer.tidakdigunakan;

public interface ShowDataSource {

    void getAllShow(String language, String page, final ShowViewModel.Callback arrayCallback);
}