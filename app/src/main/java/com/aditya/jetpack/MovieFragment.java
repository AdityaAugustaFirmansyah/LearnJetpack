package com.aditya.jetpack;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.aditya.jetpack.adapter.AdapterPage;
import com.aditya.jetpack.databinding.FragmentMovieBinding;
import com.aditya.jetpack.datasource.MovieViewModel;
import com.aditya.jetpack.model.ModelMovieView;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    AdapterPage adapterRv;
    private FragmentMovieBinding fragmentMovieBinding;
    RecyclerView recyclerViewById;
    MovieViewModel movieViewModel;

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentMovieBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.fragment_movie,container,false);

        View view = fragmentMovieBinding.getRoot();
        recyclerViewById = view.findViewById(R.id.rv_film);
        fragmentMovieBinding.rvFilm.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        final NavController navController = Navigation.findNavController(Objects.requireNonNull(getView()));

        fragmentMovieBinding.swipeRefreshMovie.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                movieViewModel.getMovies();
            }
        });

        movieViewModel.getMovieViewModelLiveData().observe(getViewLifecycleOwner(), new Observer<ModelMovieView>() {
            @Override
            public void onChanged(final ModelMovieView modelMovieView) {
                View view = fragmentMovieBinding.layoutError.getRootView();
                fragmentMovieBinding.swipeRefreshMovie.setRefreshing(modelMovieView.isStatusLoading());
                if (modelMovieView.getMsgError()!=null){
                    fragmentMovieBinding.layoutError.setVisibility(View.VISIBLE);
                    ((TextView) view.findViewById(R.id.tv_error)).setText(modelMovieView.getMsgError());
                    Log.d("TAG_MOVIE_FRAGMENT", "getMsgError: "+modelMovieView.getMsgError());
                }else {
                    fragmentMovieBinding.layoutError.setVisibility(View.GONE);
                }

                if (modelMovieView.getModelFilms() != null){
                    adapterRv = new AdapterPage(navController);
                    adapterRv.submitList(modelMovieView.getModelFilms());
                    fragmentMovieBinding.rvFilm.setAdapter(adapterRv);
                }
                Log.d("TAG_MOVIE_FRAGMENT", "onChanged: "+modelMovieView.isStatusLoading());
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
