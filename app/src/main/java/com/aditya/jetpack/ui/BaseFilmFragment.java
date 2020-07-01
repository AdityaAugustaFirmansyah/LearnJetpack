package com.aditya.jetpack.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.aditya.jetpack.R;
import com.aditya.jetpack.databinding.FragmentBaseMovieBinding;
import com.aditya.jetpack.adapter.MovieStatePagerAdapter;

import java.util.ArrayList;

public class BaseFilmFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentBaseMovieBinding fragmentBaseMovieBinding;
    private ArrayList<Fragment>fragments = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentBaseMovieBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.fragment_base_movie,container,false);
        ((AppCompatActivity)getActivity()).setSupportActionBar(fragmentBaseMovieBinding.toolbarMovie);
        setHasOptionsMenu(true);
        fragments.clear();
        fragments.add(new MovieFragment());
        fragments.add(new TvFragment());
        return fragmentBaseMovieBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentBaseMovieBinding.viewPageBaseMovie.setAdapter(new MovieStatePagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,fragments));
        fragmentBaseMovieBinding.tabLayoutBaseMovie.setupWithViewPager(fragmentBaseMovieBinding.viewPageBaseMovie);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_tollbar,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_setting){
            NavController navController = Navigation.findNavController(getView());
            navController.navigate(R.id.action_baseFragment2_to_settingFragment);
            return true;
        }else {
            return false;
        }
    }
}