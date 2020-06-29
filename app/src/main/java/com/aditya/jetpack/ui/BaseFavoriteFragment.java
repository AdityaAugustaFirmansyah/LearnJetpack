package com.aditya.jetpack.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.aditya.jetpack.R;
import com.aditya.jetpack.adapter.TvStatePagerAdapter;
import com.aditya.jetpack.databinding.FragmentBaseTvBinding;

import java.util.ArrayList;

public class BaseFavoriteFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentBaseTvBinding fragmentBaseTvBinding;
    private ArrayList<Fragment>fragments = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentBaseTvBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.fragment_base_tv, container, false);
        fragments.clear();
        fragments.add(new FavoriteMovieFragment());
        fragments.add(new FavoriteTvFragment());
        return fragmentBaseTvBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentBaseTvBinding.viewPagerTv.setAdapter(new TvStatePagerAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,fragments));
        fragmentBaseTvBinding.tabTv.setupWithViewPager(fragmentBaseTvBinding.viewPagerTv);
    }
}