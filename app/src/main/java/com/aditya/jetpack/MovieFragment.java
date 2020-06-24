package com.aditya.jetpack;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.aditya.jetpack.adapter.AdapterPage;
import com.aditya.jetpack.databinding.FragmentMovieBinding;
import com.aditya.jetpack.datasource.MovieViewModel;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    AdapterPage adapterRv;
    private FragmentMovieBinding fragmentMovieBinding;
    MovieViewModel movieViewModel;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).show();
        fragmentMovieBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.fragment_movie,container,false);

        View view = fragmentMovieBinding.getRoot();
        fragmentMovieBinding.rvFilm.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        movieViewModel = ViewModelProviders.of(getActivity()).get(MovieViewModel.class);
        navController = Navigation.findNavController(Objects.requireNonNull(getView()));
        initRv();
        initSwipeRefresh();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    
    private void initRv(){
        adapterRv = new AdapterPage(navController);
        fragmentMovieBinding.rvFilm.setAdapter(adapterRv);
        movieViewModel.getModelGenreMovieLiveData().observe(getViewLifecycleOwner(),modelGenres -> {
            adapterRv.setModelGenres(modelGenres);
            adapterRv.notifyDataSetChanged();
        });
        movieViewModel.getListLiveDataPage().observe(getViewLifecycleOwner(), results -> adapterRv.submitList(results));
        movieViewModel.getModeMovieNextPage().observe(getViewLifecycleOwner(), adapterRv::setModelMovieView);
    }

    private void initSwipeRefresh(){
        movieViewModel.getModelMovieViewLiveData().observe(getViewLifecycleOwner(),modelMovieView -> {
            fragmentMovieBinding.layoutError.progressMovie.setVisibility(modelMovieView.isStatusLoading()?View.VISIBLE:View.GONE);
            if (modelMovieView.getMsgError()!=null){
                fragmentMovieBinding.layoutError.imgBannerError.setVisibility(View.VISIBLE);
                fragmentMovieBinding.layoutError.tvError.setText(modelMovieView.getMsgError());
            }else {
                fragmentMovieBinding.layoutError.tvError.setText("");
            }
        });
        fragmentMovieBinding.swipeRefreshMovie.setOnRefreshListener(()->movieViewModel.refresh());
    }
}
