package com.aditya.jetpack;


import android.content.Context;
import android.os.Bundle;
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
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.aditya.jetpack.adapter.AdapterPage;
import com.aditya.jetpack.databinding.FragmentMovieBinding;
import com.aditya.jetpack.datasource.ModelFilm;
import com.aditya.jetpack.datasource.MovieViewModel;
import com.aditya.jetpack.datasource.ViewModelFilm;
import com.aditya.jetpack.model.ModelMovieView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    AdapterPage adapterRv;
    String statusLoading = "";
    private FragmentMovieBinding fragmentMovieBinding;
    static ViewModelFilm viewModelFilm;

    private PagedList<ModelFilm.Result> modelFilms;
    int scroolPosition;
    RecyclerView recyclerViewById;
    MovieViewModel movieViewModel;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentMovieBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.fragment_movie,container,false);

        View view = fragmentMovieBinding.getRoot();
        ModelFilm.Result result = new ModelFilm.Result();
        recyclerViewById = view.findViewById(R.id.rv_film);
        fragmentMovieBinding.rvFilm.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        movieViewModel = ViewModelProviders.of(getActivity()).get(MovieViewModel.class);
        final NavController navController = Navigation.findNavController(getView());
        movieViewModel.getListLiveDataPage().observe(getViewLifecycleOwner(), new Observer<PagedList<ModelFilm.Result>>() {
            @Override
            public void onChanged(PagedList<ModelFilm.Result> results) {
                adapterRv = new AdapterPage(navController);
                adapterRv.submitList(results);
                fragmentMovieBinding.rvFilm.setAdapter(adapterRv);
            }
        });
        fragmentMovieBinding.swipeRefreshMovie.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                movieViewModel.getListLiveDataPage();
                fragmentMovieBinding.swipeRefreshMovie.setRefreshing(false);
            }
        });

        movieViewModel.getMovieViewModelMutableLiveData().observe(getViewLifecycleOwner(), new Observer<ModelMovieView>() {
            @Override
            public void onChanged(ModelMovieView modelMovieView) {
                View view = fragmentMovieBinding.layoutError.getRootView();
                fragmentMovieBinding.swipeRefreshMovie.setRefreshing(modelMovieView.isStatusLoading());
                if (modelMovieView.getMsgError()!=null){
                    fragmentMovieBinding.layoutError.setVisibility(View.VISIBLE);
                    ((TextView) view.findViewById(R.id.tv_error)).setText(modelMovieView.getMsgError());
                }else {
                    fragmentMovieBinding.layoutError.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
