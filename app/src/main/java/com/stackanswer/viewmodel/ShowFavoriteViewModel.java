package com.stackanswer.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.stackanswer.source.local.room.showfavorite.ShowFavorite;
import com.stackanswer.source.repository.ShowFavoriteRepository;

public class ShowFavoriteViewModel extends ViewModel {

    private final ShowFavoriteRepository repository;

    public ShowFavoriteViewModel(@NonNull ShowFavoriteRepository application) {
        repository=application;
    }

    public LiveData<PagedList<ShowFavorite>> getAllShow() {
        return repository.getAllShow();
    }

    public void insert(ShowFavorite showFavorite){
        repository.insert(showFavorite);
    }

    public void update(ShowFavorite showFavorite){
        repository.update(showFavorite);
    }

    public void delete(ShowFavorite showFavorite){
        repository.delete(showFavorite);
    }

    public void deleteAll(){
        repository.deleteAll();
    }
}
