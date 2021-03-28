package com.stackanswer.tidakdigunakan;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.stackanswer.model.Movie;

import java.util.List;

public class MovieViewModel extends ViewModel {

    private final MovieRepository movieRepository;

    public MovieViewModel(MovieRepository mAcademyRepository) {
        this.movieRepository = mAcademyRepository;
    }

    public void findMovie(String language, String page) {
        _isLoading.setValue(true);
        movieRepository.getAllMovie(language, page, movieList2 -> {
            _show.setValue(movieList2);
            _isLoading.setValue(false);
        });
    }
    
    public interface Callback{
        void sukses(List<Movie> movieList);
    }

    public final MutableLiveData<List<Movie>> _show = new MutableLiveData<>();
    public LiveData<List<Movie>> getMovie() {
        return _show;
    }

    public final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();
    public LiveData<Boolean> isLoading() {
        return _isLoading;
    }

}