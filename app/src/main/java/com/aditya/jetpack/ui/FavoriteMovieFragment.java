package com.aditya.jetpack.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
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
import com.aditya.jetpack.viewmodel.MovieViewModel;

import java.util.Objects;

public class FavoriteMovieFragment extends Fragment {

    private FragmentMovieBinding fragmentMovieBinding;
    private FavoriteViewModel favoriteViewModel;
    private MovieViewModel movieViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentMovieBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.fragment_movie,container,false);
        return fragmentMovieBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        favoriteViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(FavoriteViewModel.class);
        movieViewModel = ViewModelProviders.of(getActivity()).get(MovieViewModel.class);
        initRv();
    }

    private void initRv(){
        AdapterPage adapterPage = new AdapterPage(Navigation.findNavController(Objects.requireNonNull(getView())));
        fragmentMovieBinding.rvFilm.setLayoutManager(new LinearLayoutManager(getContext()));
        favoriteViewModel.getAllResults().observe(getViewLifecycleOwner(), adapterPage::submitList);
        movieViewModel.getModelGenreMovieLiveData().observe(getViewLifecycleOwner(),adapterPage::setModelGenres);
        fragmentMovieBinding.rvFilm.setAdapter(adapterPage);
    }
}