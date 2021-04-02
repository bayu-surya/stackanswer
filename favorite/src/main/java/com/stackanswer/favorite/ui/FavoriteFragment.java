package com.stackanswer.favorite.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.stackanswer.adapter.SectionsPagerAdapter;
import com.stackanswer.favorite.R;

public class FavoriteFragment extends Fragment implements View.OnClickListener {

    private static final int[] TAB_TITLES = new int[]{R.string.movies, R.string.tv_show};

    public FavoriteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView ivSetting = view.findViewById(R.id.iv_setting);
        ivSetting.setOnClickListener(this);
        ImageView ivKembali = view.findViewById(R.id.iv_kembali);
        ivKembali.setOnClickListener(this);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getContext(),getChildFragmentManager(),TAB_TITLES);
        ViewPager viewPager = view.findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = view.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getContext(), getChildFragmentManager(),TAB_TITLES);
        FavoriteMovieKtFragment movieFragment = new FavoriteMovieKtFragment();
        FavoriteShowKtFragment showFragment = new FavoriteShowKtFragment();
        adapter.addFragment(movieFragment);
        adapter.addFragment(showFragment);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_kembali) {
            requireActivity().onBackPressed();
        }
    }
}