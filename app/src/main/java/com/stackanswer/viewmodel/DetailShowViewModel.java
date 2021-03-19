package com.stackanswer.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.stackanswer.source.repository.DetailShowRepository;
import com.stackanswer.model.Show;

public class DetailShowViewModel extends ViewModel {

    private final DetailShowRepository movieRepository;
    public Show show;

    public DetailShowViewModel(DetailShowRepository mAcademyRepository) {
        this.movieRepository = mAcademyRepository;
    }

    public final MutableLiveData<Show> _show = new MutableLiveData<>();
    public LiveData<Show> getShow() {
        return _show;
    }

    public final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();
    public LiveData<Boolean> isLoading() {
        return _isLoading;
    }

    public void findDetailShow(String language, String page) {
        _isLoading.setValue(true);
        movieRepository.getAllDetailShow(language, page, movieList2 -> {
            movieList2.setOriginalLanguage(setBahasa(movieList2.getOriginalLanguage()));
            show=movieList2;
            _show.setValue(movieList2);
            _isLoading.setValue(false);
        });
    }

    public interface Callback{
        void sukses(Show movieList);
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