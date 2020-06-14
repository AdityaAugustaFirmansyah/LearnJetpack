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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.aditya.jetpack.adapter.AdapterPageTv;
import com.aditya.jetpack.databinding.FragmentMovieBinding;
import com.aditya.jetpack.datasource.TvViewModel;
import com.aditya.jetpack.model.ModelTvView;

import java.util.Objects;

public class TvFragment extends Fragment {

    FragmentMovieBinding fragmentMovieBinding;
    TvViewModel tvViewModel;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentMovieBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.fragment_movie,container,false);
        View view = fragmentMovieBinding.getRoot();
        fragmentMovieBinding.rvFilm.setLayoutManager(new LinearLayoutManager(view.getContext()));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(TvViewModel.class);
        fragmentMovieBinding.swipeRefreshMovie.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tvViewModel.getTvs();
            }
        });
        tvViewModel.getModelTvViewLiveData().observe(getViewLifecycleOwner(), new Observer<ModelTvView>() {
            @Override
            public void onChanged(ModelTvView modelTvView) {
                fragmentMovieBinding.swipeRefreshMovie.setRefreshing(modelTvView.isLoading());
                if (modelTvView.getResults()!=null){
                    AdapterPageTv adapterPageTv = new AdapterPageTv();
                    adapterPageTv.submitList(modelTvView.getResults());
                    fragmentMovieBinding.rvFilm.setAdapter(adapterPageTv);
                }

                if (modelTvView.getMsgError()!=null){
                    fragmentMovieBinding.layoutError.setVisibility(View.VISIBLE);
                    View view = fragmentMovieBinding.layoutError;
                    ((TextView) view.findViewById(R.id.tv_error)).setText(modelTvView.getMsgError());
                    Log.d("TAG_MOVIE_FRAGMENT", "getMsgError: "+modelTvView.getMsgError());
                }else {
                    fragmentMovieBinding.layoutError.setVisibility(View.GONE);
                }
            }
        });
    }
}
