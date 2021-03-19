package com.stackanswer.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.stackanswer.source.repository.DetailMovieRepository;
import com.stackanswer.model.Movie;

public class DetailMovieViewModel extends ViewModel {

    private final DetailMovieRepository movieRepository;
    public Movie movie;

    public DetailMovieViewModel(DetailMovieRepository mAcademyRepository) {
        this.movieRepository = mAcademyRepository;
    }

    public final MutableLiveData<Movie> _show = new MutableLiveData<>();
    public LiveData<Movie> getMovie() {
        return _show;
    }

    public final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();
    public LiveData<Boolean> isLoading() {
        return _isLoading;
    }

    public void findDetailMovie(String language, String page) {
        _isLoading.setValue(true);
        movieRepository.getAllDetailMovie(language, page, movieList2 -> {
            movieList2.setOriginalLanguage(setBahasa(movieList2.getOriginalLanguage()));
            movie=movieList2;
            _show.setValue(movieList2);
            _isLoading.setValue(false);

        });
    }

    public interface Callback{
        void sukses(Movie movieList);
    }

    public String setBahasa(String string){
        String sbahasa;
        if (string.toLowerCase().equals("en")){
            sbahasa="English";
        } else {
            sbahasa=string;
        }
        return sbahasa;
    }
}