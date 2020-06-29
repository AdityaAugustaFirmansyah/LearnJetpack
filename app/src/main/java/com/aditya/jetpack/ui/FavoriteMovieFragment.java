package com.aditya.jetpack.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.aditya.jetpack.R;
import com.aditya.jetpack.adapter.AdapterPage;
import com.aditya.jetpack.databinding.FragmentMovieBinding;
import com.aditya.jetpack.viewmodel.FavoriteViewModel;

import java.util.Objects;

public class FavoriteMovieFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentMovieBinding fragmentMovieBinding;
    private FavoriteViewModel favoriteViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentMovieBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.fragment_movie,container,false);
        return fragmentMovieBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        favoriteViewModel = ViewModelProviders.of(getActivity()).get(FavoriteViewModel.class);
        initRv();
    }

    private void initRv(){
        AdapterPage adapterPage = new AdapterPage(Navigation.findNavController(Objects.requireNonNull(getView())));
        fragmentMovieBinding.rvFilm.setLayoutManager(new LinearLayoutManager(getContext()));
        favoriteViewModel.getAllResults().observe(getViewLifecycleOwner(), adapterPage::submitList);
        fragmentMovieBinding.rvFilm.setAdapter(adapterPage);
    }
}