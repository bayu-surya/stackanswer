package com.stackanswer.tidakdigunakan;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
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
import com.stackanswer.tidakdigunakan.idlingresource.EspressoIdlingResourceDetailMovie;
import com.stackanswer.source.local.room.moviefavorite.MovieFavorite;
import com.stackanswer.utils.Constan;
import com.stackanswer.utils.ImageUtils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DetailMovieFavoriteFragment extends Fragment implements View.OnClickListener {

    private com.stackanswer.databinding.FragmentDetailMovieFavoriteBinding binding;
    private MovieFavoriteViewModel viewModel;
    private List<MovieFavorite> mMovieFavorite;
    private MovieFavorite film;
    private boolean bSave=true;

    public DetailMovieFavoriteFragment() {
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = com.stackanswer.databinding.FragmentDetailMovieFavoriteBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.ivKembali.setOnClickListener(this);
        binding.ivFavorite.setOnClickListener(this);

        MovieFavoriteViewModelFactory factory = MovieFavoriteViewModelFactory.getInstance(getContext());
        viewModel = new ViewModelProvider(this, factory).get(MovieFavoriteViewModel.class);
        viewModel.getAllMovie().observe(getViewLifecycleOwner(), favoriteList -> mMovieFavorite=favoriteList);
        onStartProggress();

        setup();
    }

    public void setup(){
        Bundle arguments = getArguments();
        if (arguments != null) {
            if (arguments.containsKey(DetailMovieFavoriteFragment.class.getSimpleName())) {

                film = arguments.getParcelable(DetailMovieFavoriteFragment.class.getSimpleName());
                getArguments().remove(DetailMovieFavoriteFragment.class.getSimpleName());
                setupFilm(film);
            }
        }
    }

    private void setupFilm(MovieFavorite movie){
        binding.ivFavorite.setColorFilter(ContextCompat.getColor(requireContext(), R.color.red_active), PorterDuff.Mode.MULTIPLY);
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

        new Handler().postDelayed(this::onStopProggress, 2000);
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
        if (film!=null) {
            int[] k= new int[mMovieFavorite.size()];
            for (int m = 0; m< mMovieFavorite.size(); m++) {
                k[m]= mMovieFavorite.get(m).getId();
                if (film.getId()== mMovieFavorite.get(m).getId()){
                    if (!Constan.compareObjects(film, mMovieFavorite.get(m))) {
                        viewModel.update(film);
                    }
                }
            }
            if (!Constan.execute(k,film.getId())){
                viewModel.insert(film);
            }
        }
    }

    private void deleteFavorite(){
        if (film!=null) {
            viewModel.delete(film);
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

//      Test idling resource
        EspressoIdlingResourceDetailMovie.increment();
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

//      Test idling resource
        if (!EspressoIdlingResourceDetailMovie.getEspressoIdlingResource().isIdleNow()) {
            EspressoIdlingResourceDetailMovie.decrement();
        }
    }
}
