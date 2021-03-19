package com.stackanswer.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.stackanswer.source.repository.ShowRepository;
import com.stackanswer.model.Show;

import java.util.List;

public class ShowViewModel extends ViewModel {

    private final ShowRepository movieRepository;

    public ShowViewModel(ShowRepository mAcademyRepository) {
        this.movieRepository = mAcademyRepository;
    }

    public void findShow(String language, String page) {
        _isLoading.setValue(true);
        movieRepository.getAllShow(language, page, movieList2 -> {
            _show.setValue(movieList2);
            _isLoading.setValue(false);
        });
    }

    public interface Callback{
        void sukses(List<Show> movieList);
    }

    public final MutableLiveData<List<Show>> _show = new MutableLiveData<>();
    public LiveData<List<Show>> getShow() {
        return _show;
    }

    public final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();
    public LiveData<Boolean> isLoading() {
        return _isLoading;
    }
}