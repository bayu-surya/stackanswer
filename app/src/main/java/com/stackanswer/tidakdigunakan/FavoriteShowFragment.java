package com.stackanswer.tidakdigunakan;

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
import com.stackanswer.databinding.FragmentFavoriteShowBinding;
import com.stackanswer.tidakdigunakan.idlingresource.EspressoIdlingResourceFavoriteShow;
import com.stackanswer.ui.HomeFragment;

import org.jetbrains.annotations.NotNull;

public class FavoriteShowFragment extends Fragment {

    private RecyclerView rvMovie;
    private com.stackanswer.databinding.FragmentFavoriteShowBinding binding;
    private ShowFavoriteAdapter adapter;

    public FavoriteShowFragment() {
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavoriteShowBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ShowFavoriteViewModelFactory factory = ShowFavoriteViewModelFactory.getInstance(getContext());
        ShowFavoriteViewModel showFavoriteViewModel = new ViewModelProvider(this, factory).get(ShowFavoriteViewModel.class);

        onStartProggress();
        rvMovie = binding.rvShow;
        setupRVmovie();

        showFavoriteViewModel.getAllShow().observe(getViewLifecycleOwner(), movieFavorites -> adapter.submitList(movieFavorites));
    }

    private void onStartProggress(){
        binding.rvShow.setVisibility(View.GONE);
        binding.shimmer.setVisibility(View.VISIBLE);
        binding.shimmer2.setVisibility(View.VISIBLE);
        binding.shimmer3.setVisibility(View.VISIBLE);
        binding.shimmer.startShimmerAnimation();
        binding.shimmer2.startShimmerAnimation();
        binding.shimmer3.startShimmerAnimation();
//        Test idling resource
        EspressoIdlingResourceFavoriteShow.increment();
    }

    private void onStopProggress(){
        binding.rvShow.setVisibility(View.VISIBLE);
        binding.shimmer.setVisibility(View.GONE);
        binding.shimmer2.setVisibility(View.GONE);
        binding.shimmer3.setVisibility(View.GONE);
        binding.shimmer.stopShimmerAnimation();
        binding.shimmer2.stopShimmerAnimation();
        binding.shimmer3.stopShimmerAnimation();
        if (!EspressoIdlingResourceFavoriteShow.getEspressoIdlingResource().isIdleNow()) {
            EspressoIdlingResourceFavoriteShow.decrement();
        }
    }

    private void setupRVmovie() {
        adapter = new ShowFavoriteAdapter(getContext());
        rvMovie.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false) );
        rvMovie.setAdapter(adapter);
        adapter.setCallback((films, position) -> {
            FragmentManager mFragmentManager = ((FragmentActivity) requireContext()).getSupportFragmentManager();
            DetailShowFavoriteFragment fragment = new DetailShowFavoriteFragment();
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
}