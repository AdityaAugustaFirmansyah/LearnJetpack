package com.aditya.jetpack.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.aditya.jetpack.R;
import com.aditya.jetpack.databinding.FragmentBaseMovieBinding;
import com.aditya.jetpack.adapter.MovieStatePagerAdapter;

import java.util.ArrayList;

public class BaseMovieFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentBaseMovieBinding fragmentBaseMovieBinding;
    private ArrayList<Fragment>fragments = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        fragmentBaseMovieBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.fragment_base_movie,container,false);
        fragments.clear();
        fragments.add(new MovieFragment());
        fragments.add(new FavoriteMovieFragment());
        return fragmentBaseMovieBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentBaseMovieBinding.viewPageBaseMovie.setAdapter(new MovieStatePagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,fragments));
        fragmentBaseMovieBinding.tabLayoutBaseMovie.setupWithViewPager(fragmentBaseMovieBinding.viewPageBaseMovie);
    }
}