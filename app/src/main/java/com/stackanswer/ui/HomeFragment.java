package com.stackanswer.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.stackanswer.R;
import com.stackanswer.adapter.SectionsPagerAdapter;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private static final int[] TAB_TITLES = new int[]{R.string.movies, R.string.tv_show};

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView ivSetting = view.findViewById(R.id.iv_setting);
        ivSetting.setOnClickListener(this);
        FloatingActionButton fabAdd = view.findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(this);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getContext(),getChildFragmentManager(),TAB_TITLES);
        ViewPager viewPager = view.findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = view.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getContext(), getChildFragmentManager(),TAB_TITLES);
        MovieKtFragment movieFragment = new MovieKtFragment();
        ShowKtFragment showFragment = new ShowKtFragment();
        adapter.addFragment(movieFragment);
        adapter.addFragment(showFragment);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab_add) {
            Uri uri = Uri.parse("stackanswer://favorite");
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(uri);
            startActivity(i);
        }
    }
}
