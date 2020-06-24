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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.aditya.jetpack.adapter.AdapterPageTv;
import com.aditya.jetpack.databinding.FragmentMovieBinding;
import com.aditya.jetpack.datasource.TvViewModel;

import java.util.Objects;

public class TvFragment extends Fragment {

    FragmentMovieBinding fragmentMovieBinding;
    TvViewModel tvViewModel;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).show();
        fragmentMovieBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.fragment_movie,container,false);
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
           fragmentMovieBinding.layoutError.progressMovie.setVisibility(modelTvView.isLoading()?View.VISIBLE:View.GONE);
           if (modelTvView.getMsgError()!=null){
               fragmentMovieBinding.layoutError.tvError.setText(modelTvView.getMsgError());
               fragmentMovieBinding.layoutError.imgBannerError.setVisibility(View.VISIBLE);
           }else {
               fragmentMovieBinding.layoutError.tvError.setText("");
               fragmentMovieBinding.layoutError.imgBannerError.setVisibility(View.GONE);
           }
        });
    }

    private void initRv() {
        AdapterPageTv adapterPageTv = new AdapterPageTv();
        fragmentMovieBinding.rvFilm.setAdapter(adapterPageTv);
        tvViewModel.getModelGenreLiveData().observe(getViewLifecycleOwner(),adapterPageTv::setModelGenres);
        tvViewModel.getListLiveData().observe(getViewLifecycleOwner(),adapterPageTv::submitList);
        tvViewModel.getNetworkStateLiveData().observe(getViewLifecycleOwner(),adapterPageTv::setModelTvView);
    }
}
