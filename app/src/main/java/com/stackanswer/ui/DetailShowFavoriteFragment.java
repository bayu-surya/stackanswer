package com.stackanswer.ui;

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
import com.stackanswer.source.local.room.showfavorite.ShowFavorite;
import com.stackanswer.idlingresource.EspressoIdlingResourceDetailShow;
import com.stackanswer.utils.Constan;
import com.stackanswer.utils.ImageUtils;
import com.stackanswer.viewmodel.ShowFavoriteViewModel;
import com.stackanswer.viewmodel.factory.ShowFavoriteViewModelFactory;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DetailShowFavoriteFragment extends Fragment implements View.OnClickListener {

    private com.stackanswer.databinding.FragmentDetailShowFavoriteBinding binding;
    private ShowFavoriteViewModel viewModel;
    private List<ShowFavorite> mShowFavorite;
    private ShowFavorite film;
    private boolean bSave=true;

    public DetailShowFavoriteFragment() {
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = com.stackanswer.databinding.FragmentDetailShowFavoriteBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.ivKembali.setOnClickListener(this);
        binding.ivFavorite.setOnClickListener(this);

        ShowFavoriteViewModelFactory factory2 = ShowFavoriteViewModelFactory.getInstance(getContext());
        viewModel = new ViewModelProvider(this, factory2).get(ShowFavoriteViewModel.class);
        viewModel.getAllShow().observe(getViewLifecycleOwner(), favoriteList -> mShowFavorite=favoriteList);
        onStartProggress();

        setup();
    }

    public void setup(){
        Bundle arguments = getArguments();
        if (arguments != null) {
            if (arguments.containsKey(DetailShowFavoriteFragment.class.getSimpleName())) {

                film = arguments.getParcelable(DetailShowFavoriteFragment.class.getSimpleName());
                getArguments().remove(DetailShowFavoriteFragment.class.getSimpleName());
                setupFilm(film);
            }
        }
    }

    private void setupFilm(ShowFavorite show){
        binding.ivFavorite.setColorFilter(ContextCompat.getColor(requireContext(), R.color.red_active), PorterDuff.Mode.MULTIPLY);
        binding.tvDetailUtama.setText(show.getOverview());
        binding.tvJudul.setText(show.getName());
        binding.tvBahasa.setText(show.getOriginalLanguage());
        binding.tvTayang.setText(show.getFirstAirDate());
        binding.tvRating.setText(String.valueOf(show.getVoteAverage()));
        String genre=String.valueOf(show.getGenreIds());
        if (String.valueOf(show.getGenreIds()).contains("[") || String.valueOf(show.getGenreIds()).contains("]")){
            genre=genre.replaceAll("\\[", "").replaceAll("]", "");
        }
        binding.tvGenre.setText(genre);

        if (show.getPosterPath()!=null && !show.getPosterPath().equals("") && !show.getPosterPath().equals("null")) {
            String url= BuildConfig.BASE_URL_IMAGE+show.getPosterPath();
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

            int[] k= new int[mShowFavorite.size()];
            for (int m = 0; m< mShowFavorite.size(); m++) {
                k[m]= mShowFavorite.get(m).getId();
                if (film.getId()== mShowFavorite.get(m).getId()){
                    if (!Constan.compareObjects(film, mShowFavorite.get(m))) {
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
        EspressoIdlingResourceDetailShow.increment();
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
        if (!EspressoIdlingResourceDetailShow.getEspressoIdlingResource().isIdleNow()) {
            EspressoIdlingResourceDetailShow.decrement();
        }
    }
}
