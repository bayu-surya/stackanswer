package com.stackanswer.ui;

import android.os.Bundle;
import android.util.Log;
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
import com.stackanswer.adapter.MovieAdapter;
import com.stackanswer.databinding.FragmentMovieBinding;
import com.stackanswer.idlingresource.EspressoIdlingResource;
import com.stackanswer.model.Movie;
import com.stackanswer.viewmodel.MovieViewModel;
import com.stackanswer.viewmodel.factory.ViewModelFactory;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MovieFragment extends Fragment {

    private RecyclerView rvMovie;
    private com.stackanswer.databinding.FragmentMovieBinding binding;

    public MovieFragment() {
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMovieBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    public MovieViewModel viewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
        viewModel = new ViewModelProvider(this, factory).get(MovieViewModel.class);
        viewModel.getMovie().observe(getViewLifecycleOwner(), shows -> {
            setupRVmovie(shows);
            Log.d(TAG, "onViewCreated: "+shows.size());
        });
        viewModel.isLoading().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading) {
                onStartProggress();

//                Test idling resource
                EspressoIdlingResource.increment();
            } else {
                onStopProggress();
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                    EspressoIdlingResource.decrement();
                }
            }
        });

        rvMovie = binding.rvMovie;

        viewModel.findMovie("en-US", "1");
    }

    private static final String TAG = "MainViewModel";

    private void setupRVmovie(final List<Movie> filmList) {
        Log.d(TAG, "loadMovie: 2 "+filmList.size());
        if (filmList.size() != 0) {
            MovieAdapter adapter = new MovieAdapter(getContext(), filmList);
            rvMovie.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false) );
            rvMovie.setAdapter(adapter);
            adapter.setCallback((films, position) -> {
                FragmentManager mFragmentManager = ((FragmentActivity) requireContext()).getSupportFragmentManager();
                DetailMovieFragment detailMovieFragment = new DetailMovieFragment();
                Bundle b = new Bundle();
                b.putParcelable(DetailMovieFragment.class.getSimpleName(), films);
                detailMovieFragment.setArguments(b);
                String backStateName = HomeFragment.class.getSimpleName();
                mFragmentManager
                        .beginTransaction()
                        .replace(R.id.container_layout, detailMovieFragment, DetailMovieFragment.class.getSimpleName())
                        .addToBackStack(backStateName)
                        .commit();
            });
        }
    }

    private void onStartProggress(){
        binding.shimmer.setVisibility(View.VISIBLE);
        binding.shimmer2.setVisibility(View.VISIBLE);
        binding.shimmer3.setVisibility(View.VISIBLE);
        binding.shimmer.startShimmerAnimation();
        binding.shimmer2.startShimmerAnimation();
        binding.shimmer3.startShimmerAnimation();
    }

    private void onStopProggress(){
        binding.shimmer.setVisibility(View.GONE);
        binding.shimmer2.setVisibility(View.GONE);
        binding.shimmer3.setVisibility(View.GONE);
        binding.shimmer.stopShimmerAnimation();
        binding.shimmer2.stopShimmerAnimation();
        binding.shimmer3.stopShimmerAnimation();
    }
}
