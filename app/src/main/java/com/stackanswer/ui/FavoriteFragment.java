package com.stackanswer.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.stackanswer.R;
import com.stackanswer.adapter.SectionsPagerAdapter;

public class FavoriteFragment extends Fragment implements View.OnClickListener {

    private static final int[] TAB_TITLES = new int[]{R.string.movies, R.string.tv_show};

    public FavoriteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        ImageView ivSetting = view.findViewById(R.id.iv_setting);
        ivSetting.setOnClickListener(this);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getContext(),getChildFragmentManager(),TAB_TITLES);
        ViewPager viewPager = view.findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = view.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        setupViewPager(viewPager);

        return  view;
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getContext(), getChildFragmentManager(),TAB_TITLES);
        FavoriteMovieFragment movieFragment = new FavoriteMovieFragment();
        FavoriteShowFragment showFragment = new FavoriteShowFragment();
        adapter.addFragment(movieFragment);
        adapter.addFragment(showFragment);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
    }
}