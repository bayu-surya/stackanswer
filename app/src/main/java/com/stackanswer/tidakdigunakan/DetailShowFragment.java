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
import com.stackanswer.source.local.room.showfavorite.ShowFavorite;
import com.stackanswer.tidakdigunakan.idlingresource.EspressoIdlingResourceDetail2;
import com.stackanswer.model.Show;
import com.stackanswer.utils.Constan;
import com.stackanswer.utils.ImageUtils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DetailShowFragment extends Fragment implements View.OnClickListener {

    private DetailShowViewModel viewModel;
    private com.stackanswer.databinding.FragmentDetailShowBinding binding;
    private static final String TAG = "MainViewModel";
    private ShowFavoriteViewModel showFavoriteViewModel;
    private List<ShowFavorite> mShowFavorite;
    private Show film;
    private boolean bSave=false;

    public DetailShowFragment() {
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = com.stackanswer.databinding.FragmentDetailShowBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.ivKembali.setOnClickListener(this);
        binding.ivFavorite.setOnClickListener(this);

        DetailShowViewModelFactory factory = DetailShowViewModelFactory.getInstance(getActivity());
        viewModel = new ViewModelProvider(this, factory).get(DetailShowViewModel.class);
        viewModel.isLoading().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading) {
                onStartProggress();

//                Test idling resource
                EspressoIdlingResourceDetail2.increment();
            } else {
                onStopProggress();
                if (!EspressoIdlingResourceDetail2.getEspressoIdlingResource().isIdleNow()) {
                    EspressoIdlingResourceDetail2.decrement();
                }
            }
        });

        ShowFavoriteViewModelFactory factory2 = ShowFavoriteViewModelFactory.getInstance(getContext());
        showFavoriteViewModel = new ViewModelProvider(this, factory2).get(ShowFavoriteViewModel.class);
        showFavoriteViewModel.getAllShow().observe(getViewLifecycleOwner(), favoriteList -> {
            mShowFavorite=favoriteList;
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
        for (int i=0;i<mShowFavorite.size();i++){
            if (mShowFavorite.get(i).getId()==film.getId()){
                binding.ivFavorite.setColorFilter(ContextCompat.getColor(requireContext(), R.color.red_active), PorterDuff.Mode.MULTIPLY);
                bSave=true;
            }
        }
    }

    public void setup(){
        Bundle arguments = getArguments();
        if (arguments != null) {
            if (arguments.containsKey(DetailShowFragment.class.getSimpleName())) {

                film = arguments.getParcelable(DetailShowFragment.class.getSimpleName());
                getArguments().remove(DetailShowFragment.class.getSimpleName());
                viewModel.getShow().observe(getViewLifecycleOwner(), shows -> {
                    setupFilm(shows);
                    Log.d(TAG, "onViewCreated: "+shows.toString());
                });
                viewModel.findDetailShow("en-US", String.valueOf(film.getId()));
            }
        }
    }

    private void setupFilm(Show show){
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
        if (viewModel.show!=null) {
            ShowFavorite show = new ShowFavorite();
            show.setId(viewModel.show.getId());
            show.setOverview(viewModel.show.getOverview());
            show.setOriginalLanguage(viewModel.show.getOriginalLanguage());
            show.setFirstAirDate(viewModel.show.getFirstAirDate());
            show.setOriginCountry(viewModel.show.getOriginCountry().toString());
            show.setOriginalName(viewModel.show.getOriginalName());
            show.setGenreIds(viewModel.show.getGenreIds().toString());
            show.setPosterPath(viewModel.show.getPosterPath());
            show.setBackdropPath(viewModel.show.getBackdropPath());
            show.setPopularity(viewModel.show.getPopularity());
            show.setVoteAverage(viewModel.show.getVoteAverage());
            show.setName(viewModel.show.getName());
            show.setVoteCount(viewModel.show.getVoteCount());

            int[] k= new int[mShowFavorite.size()];
            for (int m = 0; m< mShowFavorite.size(); m++) {
                k[m]= mShowFavorite.get(m).getId();
                if (show.getId()== mShowFavorite.get(m).getId()){
                    if (!Constan.compareObjects(show, mShowFavorite.get(m))) {
                        showFavoriteViewModel.update(show);
                    }
                }
            }
            if (!Constan.execute(k,show.getId())){
                showFavoriteViewModel.insert(show);
            }
        }
    }
    private void deleteFavorite(){
        if (viewModel.show!=null) {
            ShowFavorite show = new ShowFavorite();
            show.setId(viewModel.show.getId());
            show.setOverview(viewModel.show.getOverview());
            show.setOriginalLanguage(viewModel.show.getOriginalLanguage());
            show.setFirstAirDate(viewModel.show.getFirstAirDate());
            show.setOriginCountry(viewModel.show.getOriginCountry().toString());
            show.setOriginalName(viewModel.show.getOriginalName());
            show.setGenreIds(viewModel.show.getGenreIds().toString());
            show.setPosterPath(viewModel.show.getPosterPath());
            show.setBackdropPath(viewModel.show.getBackdropPath());
            show.setPopularity(viewModel.show.getPopularity());
            show.setVoteAverage(viewModel.show.getVoteAverage());
            show.setName(viewModel.show.getName());
            show.setVoteCount(viewModel.show.getVoteCount());

            showFavoriteViewModel.delete(show);
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
