package com.stackanswer.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.stackanswer.source.local.room.moviefavorite.MovieFavorite;
import com.stackanswer.source.repository.MovieFavoriteRepository;

public class MovieFavoriteViewModel extends ViewModel {

    private final MovieFavoriteRepository repository;

    public MovieFavoriteViewModel(@NonNull MovieFavoriteRepository application) {
        repository=application;
    }

    public LiveData<PagedList<MovieFavorite>> getAllMovie() {
        return repository.getAllMovie();
    }

    public void insert(MovieFavorite movieFavorite){
        repository.insert(movieFavorite);
    }

    public void update(MovieFavorite movieFavorite){
        repository.update(movieFavorite);
    }

    public void delete(MovieFavorite movieFavorite){
        repository.delete(movieFavorite);
    }

    public void deleteAll(){
        repository.deleteAll();
    }
}
