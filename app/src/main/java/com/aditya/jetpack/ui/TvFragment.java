package com.aditya.jetpack.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.aditya.jetpack.R;
import com.aditya.jetpack.adapter.AdapterPageTv;
import com.aditya.jetpack.databinding.FragmentMovieBinding;
import com.aditya.jetpack.viewmodel.TvViewModel;

import java.util.Objects;

public class TvFragment extends Fragment {

    FragmentMovieBinding fragmentMovieBinding;
    TvViewModel tvViewModel;
    AdapterPageTv adapterPageTv;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentMovieBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_movie,container,false);
        View view = fragmentMovieBinding.getRoot();
        fragmentMovieBinding.rvFilm.setLayoutManager(new LinearLayoutManager(view.getContext()));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(TvViewModel.class);
        initNetworkState();
        initRv();
    }

    private void initNetworkState() {
        tvViewModel.getModelTvViewLiveData().observe(getViewLifecycleOwner(), modelTvView -> {
           if (adapterPageTv.getCurrentList()!=null&&adapterPageTv.getCurrentList().size()>0){
               fragmentMovieBinding.swipeRefreshMovie.setRefreshing(modelTvView.isLoading());
           }else {
               Log.d("TAG_LOADING_FRAGMENT", "initSwipeRefresh: "+modelTvView.isLoading());
               fragmentMovieBinding.layoutError.progressMovie.setVisibility(modelTvView.isLoading()?View.VISIBLE:View.GONE);
           }
           if (modelTvView.getMsgError()!=null){
               fragmentMovieBinding.layoutError.tvError.setText(modelTvView.getMsgError());
               fragmentMovieBinding.layoutError.imgBannerError.setVisibility(View.VISIBLE);
           }else {
               fragmentMovieBinding.layoutError.tvError.setText("");
               fragmentMovieBinding.layoutError.imgBannerError.setVisibility(View.GONE);
           }
        });
        fragmentMovieBinding.swipeRefreshMovie.setOnRefreshListener(()->{
            tvViewModel.refresh();
        });
    }

    private void initRv() {
        adapterPageTv = new AdapterPageTv();
        fragmentMovieBinding.rvFilm.setAdapter(adapterPageTv);
        tvViewModel.getModelGenreLiveData().observe(getViewLifecycleOwner(),adapterPageTv::setModelGenres);
        tvViewModel.getListLiveData().observe(getViewLifecycleOwner(),adapterPageTv::submitList);
        tvViewModel.getNetworkStateLiveData().observe(getViewLifecycleOwner(),adapterPageTv::setModelTvView);
    }
}
