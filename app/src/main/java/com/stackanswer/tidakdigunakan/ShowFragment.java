package com.stackanswer.tidakdigunakan;

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
import com.stackanswer.adapter.ShowAdapter;
import com.stackanswer.databinding.FragmentShowBinding;
import com.stackanswer.tidakdigunakan.idlingresource.EspressoIdlingResourceShow;
import com.stackanswer.model.Show;
import com.stackanswer.ui.HomeFragment;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ShowFragment extends Fragment {

    private RecyclerView rvMovie;
    private com.stackanswer.databinding.FragmentShowBinding binding;

    public ShowFragment() {
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentShowBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ShowViewModelFactory factory = ShowViewModelFactory.getInstance(getActivity());
        ShowViewModel viewModel = new ViewModelProvider(this, factory).get(ShowViewModel.class);
        viewModel.getShow().observe(getViewLifecycleOwner(), shows -> {
            setupRVmovie(shows);
            Log.d(TAG, "onViewCreated: "+shows.size());
        });
        viewModel.isLoading().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading) {
                onStartProggress();

//                Test idling resource
                EspressoIdlingResourceShow.increment();
            } else {
                onStopProggress();
                if (!EspressoIdlingResourceShow.getEspressoIdlingResource().isIdleNow()) {
                    EspressoIdlingResourceShow.decrement();
                }
            }
        });

        rvMovie = binding.rvShow;

        viewModel.findShow("en-US", "1");

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

    private static final String TAG = "MainViewModel";

    private void setupRVmovie(final List<Show> filmList) {
        if (filmList.size() != 0) {
            ShowAdapter adapter = new ShowAdapter(getContext(), filmList);
            rvMovie.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false) );
            rvMovie.setAdapter(adapter);
            adapter.setCallback((films, position) -> {
                FragmentManager mFragmentManager = ((FragmentActivity) requireContext()).getSupportFragmentManager();
                DetailShowFragment detailMovieFragment = new DetailShowFragment();
                Bundle b = new Bundle();
                b.putParcelable(DetailShowFragment.class.getSimpleName(), films);
                detailMovieFragment.setArguments(b);
                String backStateName = HomeFragment.class.getSimpleName();
                mFragmentManager
                        .beginTransaction()
                        .replace(R.id.container_layout, detailMovieFragment, DetailShowFragment.class.getSimpleName())
                        .addToBackStack(backStateName)
                        .commit();
            });
        }
    }
}
