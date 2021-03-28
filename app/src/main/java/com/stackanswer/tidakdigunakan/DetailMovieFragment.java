package com.stackanswer.tidakdigunakan;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.stackanswer.BuildConfig;
import com.stackanswer.R;
import com.stackanswer.source.local.room.moviefavorite.MovieFavorite;
import com.stackanswer.tidakdigunakan.idlingresource.EspressoIdlingResourceDetail;
import com.stackanswer.model.Movie;
import com.stackanswer.utils.Constan;
import com.stackanswer.utils.ImageUtils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DetailMovieFragment extends Fragment implements View.OnClickListener {

    public DetailMovieViewModel viewModel;
    private com.stackanswer.databinding.FragmentDetailMovieBinding binding;
    private static final String TAG = "MainViewModel";
    private MovieFavoriteViewModel movieFavoriteViewModel;
    private List<MovieFavorite> mMovieFavorites;
    private Movie film;
    private boolean bSave=false;

    public DetailMovieFragment() {
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = com.stackanswer.databinding.FragmentDetailMovieBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.ivKembali.setOnClickListener(this);
        binding.ivFavorite.setOnClickListener(this);

        DetailMovieViewModelFactory factory = DetailMovieViewModelFactory.getInstance(getActivity());
        viewModel = new ViewModelProvider(this, factory).get(DetailMovieViewModel.class);
        viewModel.isLoading().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading) {
                onStartProggress();

//                Test idling resource
                EspressoIdlingResourceDetail.increment();
            } else {
                onStopProggress();
                if (!EspressoIdlingResourceDetail.getEspressoIdlingResource().isIdleNow()) {
                    EspressoIdlingResourceDetail.decrement();
                }
            }
        });


        MovieFavoriteViewModelFactory factory2 = MovieFavoriteViewModelFactory.getInstance(getContext());
        movieFavoriteViewModel = new ViewModelProvider(this, factory2).get(MovieFavoriteViewModel.class);
        movieFavoriteViewModel.getAllMovie().observe(getViewLifecycleOwner(), favoriteList -> {
            mMovieFavorites=favoriteList;
            whileShow();
        });

        setup();
    }

    private void whileShow(){
        if (film==null) {
            while (film == null) {
                new Handler().postDelayed(() -> {
                    if (film!=null) {
                        ivFovorite();
                    }
                }, 800);
            }
        } else {
            ivFovorite();
        }
    }

    private void ivFovorite(){
        for (int i=0;i<mMovieFavorites.size();i++){
            if (mMovieFavorites.get(i).getId()==film.getId()){
                binding.ivFavorite.setColorFilter(ContextCompat.getColor(requireContext(), R.color.red_active), PorterDuff.Mode.MULTIPLY);
                bSave=true;
            }
        }
    }

    public void setup(){
        Bundle arguments = getArguments();
        if (arguments != null) {
            if (arguments.containsKey(DetailMovieFragment.class.getSimpleName())) {

                film = arguments.getParcelable(DetailMovieFragment.class.getSimpleName());
                getArguments().remove(DetailMovieFragment.class.getSimpleName());
                viewModel.getMovie().observe(getViewLifecycleOwner(), shows -> {
                    setupFilm(shows);
                    Log.d(TAG, "onViewCreated: "+shows.toString());
                });
                viewModel.findDetailMovie("en-US", String.valueOf(film.getId()));
            }
        }
    }

    private void setupFilm(Movie movie){
        binding.tvDetailUtama.setText(movie.getOverview());
        binding.tvJudul.setText(movie.getTitle());
        binding.tvBahasa.setText(movie.getOriginalLanguage());
        binding.tvTayang.setText(movie.getReleaseDate());
        binding.tvRating.setText(String.valueOf(movie.getVoteAverage()));
        String genre=String.valueOf(movie.getGenreIds());
        if (String.valueOf(movie.getGenreIds()).contains("[") || String.valueOf(movie.getGenreIds()).contains("]")){
            genre=genre.replaceAll("\\[", "").replaceAll("]", "");
        }
        binding.tvGenre.setText(genre);

        if (movie.getPosterPath()!=null && !movie.getPosterPath().equals("") && !movie.getPosterPath().equals("null")) {
            String url= BuildConfig.BASE_URL_IMAGE+movie.getPosterPath();
            ImageUtils.fromUrlWithSize(getContext(), url, binding.ivPoster, 200, 280);
            ImageUtils.fromUrlWithSize(getContext(), url, binding.ivPoster4, 100, 140);
            ImageUtils.fromUrlWithSize(getContext(), url, binding.ivPoster3, 100, 140);
            ImageUtils.fromUrlWithSize(getContext(), url, binding.ivPoster2, 100, 140);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_kembali) {
            requireActivity().onBackPressed();
        } else if (v.getId() == R.id.iv_favorite) {
            if (!bSave) {
                saveFavorite();
                bSave=true;
                binding.ivFavorite.setColorFilter(ContextCompat.getColor(requireContext(), R.color.red_active), PorterDuff.Mode.MULTIPLY);
            } else {
                deleteFavorite();
                bSave=false;
                binding.ivFavorite.clearColorFilter();
            }
        }
    }

    private void saveFavorite(){
        if (viewModel.movie!=null) {
            MovieFavorite movie = new MovieFavorite();
            movie.setId(viewModel.movie.getId());
            movie.setOverview(viewModel.movie.getOverview());
            movie.setOriginalLanguage(viewModel.movie.getOriginalLanguage());
            movie.setOriginalTitle(viewModel.movie.getOriginalTitle());
            movie.setVideo(viewModel.movie.isVideo());
            movie.setTitle(viewModel.movie.getTitle());
            movie.setGenreIds(viewModel.movie.getGenreIds().toString());
            movie.setPosterPath(viewModel.movie.getPosterPath());
            movie.setBackdropPath(viewModel.movie.getBackdropPath());
            movie.setReleaseDate(viewModel.movie.getReleaseDate());
            movie.setPopularity(viewModel.movie.getPopularity());
            movie.setVoteAverage(viewModel.movie.getVoteAverage());
            movie.setAdult(viewModel.movie.isAdult());
            movie.setVoteCount(viewModel.movie.getVoteCount());

            int[] k= new int[mMovieFavorites.size()];
            for (int m = 0; m< mMovieFavorites.size(); m++) {
                k[m]= mMovieFavorites.get(m).getId();
                if (movie.getId()== mMovieFavorites.get(m).getId()){
                    if (!Constan.compareObjects(movie, mMovieFavorites.get(m))) {
                        movieFavoriteViewModel.update(movie);
                    }
                }
            }
            if (!Constan.execute(k,movie.getId())){
                movieFavoriteViewModel.insert(movie);
            }
        }
    }

    private void deleteFavorite(){
        if (viewModel.movie!=null) {
            MovieFavorite movie = new MovieFavorite();
            movie.setId(viewModel.movie.getId());
            movie.setOverview(viewModel.movie.getOverview());
            movie.setOriginalLanguage(viewModel.movie.getOriginalLanguage());
            movie.setOriginalTitle(viewModel.movie.getOriginalTitle());
            movie.setVideo(viewModel.movie.isVideo());
            movie.setTitle(viewModel.movie.getTitle());
            movie.setGenreIds(viewModel.movie.getGenreIds().toString());
            movie.setPosterPath(viewModel.movie.getPosterPath());
            movie.setBackdropPath(viewModel.movie.getBackdropPath());
            movie.setReleaseDate(viewModel.movie.getReleaseDate());
            movie.setPopularity(viewModel.movie.getPopularity());
            movie.setVoteAverage(viewModel.movie.getVoteAverage());
            movie.setAdult(viewModel.movie.isAdult());
            movie.setVoteCount(viewModel.movie.getVoteCount());

            movieFavoriteViewModel.delete(movie);
        }
    }

    private void onStartProggress(){
        binding.ivFavorite.setVisibility(View.GONE);
        binding.clBody.setVisibility(View.GONE);
        binding.shimmer.setVisibility(View.VISIBLE);
        binding.shimmer2.setVisibility(View.VISIBLE);
        binding.shimmer3.setVisibility(View.VISIBLE);
        binding.shimmer.startShimmerAnimation();
        binding.shimmer2.startShimmerAnimation();
        binding.shimmer3.startShimmerAnimation();
    }

    private void onStopProggress(){
        binding.ivFavorite.setVisibility(View.VISIBLE);
        binding.clBody.setVisibility(View.VISIBLE);
        binding.shimmer.setVisibility(View.GONE);
        binding.shimmer2.setVisibility(View.GONE);
        binding.shimmer3.setVisibility(View.GONE);
        binding.shimmer.stopShimmerAnimation();
        binding.shimmer2.stopShimmerAnimation();
        binding.shimmer3.stopShimmerAnimation();
    }
}
