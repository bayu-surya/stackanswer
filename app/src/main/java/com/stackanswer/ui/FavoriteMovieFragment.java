package com.stackanswer.ui;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stackanswer.R;
import com.stackanswer.adapter.MovieFavoriteAdapter;
import com.stackanswer.databinding.FragmentFavoriteMovieBinding;
import com.stackanswer.idlingresource.EspressoIdlingResourceFavorite;
import com.stackanswer.viewmodel.MovieFavoriteViewModel;
import com.stackanswer.viewmodel.factory.MovieFavoriteViewModelFactory;

import org.jetbrains.annotations.NotNull;

public class FavoriteMovieFragment extends Fragment {

    private RecyclerView rvMovie;
    private com.stackanswer.databinding.FragmentFavoriteMovieBinding binding;
    private MovieFavoriteAdapter adapter;

    public FavoriteMovieFragment() {
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavoriteMovieBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MovieFavoriteViewModelFactory factory = MovieFavoriteViewModelFactory.getInstance(getContext());
        MovieFavoriteViewModel movieFavoriteViewModel = new ViewModelProvider(this, factory).get(MovieFavoriteViewModel.class);

        onStartProggress();
        rvMovie = binding.rvMovie;
        setupRVmovie();

        movieFavoriteViewModel.getAllMovie().observe(getViewLifecycleOwner(), movieFavorites -> adapter.submitList(movieFavorites));
    }

    private void setupRVmovie() {
        adapter = new MovieFavoriteAdapter(getContext());
        rvMovie.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false) );
        rvMovie.setAdapter(adapter);
        adapter.setCallback((films, position) -> {
            FragmentManager mFragmentManager = ((FragmentActivity) requireContext()).getSupportFragmentManager();
            DetailMovieFavoriteFragment fragment = new DetailMovieFavoriteFragment();
            Bundle b = new Bundle();
            b.putParcelable(fragment.getClass().getSimpleName(), films);
            fragment.setArguments(b);
            String backStateName = HomeFragment.class.getSimpleName();
            mFragmentManager
                    .beginTransaction()
                    .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(backStateName)
                    .commit();
        });
        new Handler().postDelayed(this::onStopProggress, 2000);
    }

    private void onStartProggress(){
        binding.rvMovie.setVisibility(View.GONE);
        binding.shimmer.setVisibility(View.VISIBLE);
        binding.shimmer2.setVisibility(View.VISIBLE);
        binding.shimmer3.setVisibility(View.VISIBLE);
        binding.shimmer.startShimmerAnimation();
        binding.shimmer2.startShimmerAnimation();
        binding.shimmer3.startShimmerAnimation();
//        Test idling resource
        EspressoIdlingResourceFavorite.increment();
    }

    private void onStopProggress(){
        binding.rvMovie.setVisibility(View.VISIBLE);
        binding.shimmer.setVisibility(View.GONE);
        binding.shimmer2.setVisibility(View.GONE);
        binding.shimmer3.setVisibility(View.GONE);
        binding.shimmer.stopShimmerAnimation();
        binding.shimmer2.stopShimmerAnimation();
        binding.shimmer3.stopShimmerAnimation();
        if (!EspressoIdlingResourceFavorite.getEspressoIdlingResource().isIdleNow()) {
            EspressoIdlingResourceFavorite.decrement();
        }
    }
}