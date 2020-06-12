package com.aditya.jetpack;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.aditya.jetpack.adapter.AdapterPageTv;
import com.aditya.jetpack.databinding.FragmentMovieBinding;
import com.aditya.jetpack.datasource.ModelTv;
import com.aditya.jetpack.datasource.TvViewModel;

public class TvFragment extends Fragment {

    private static final String TAG = "TAG_FRAGMENT_TV";
    FragmentMovieBinding fragmentMovieBinding;
    TvViewModel tvViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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
        tvViewModel = ViewModelProviders.of(getActivity()).get(TvViewModel.class);
        tvViewModel.getListLiveData().observe(getViewLifecycleOwner(), new Observer<PagedList<ModelTv.Result>>() {
            @Override
            public void onChanged(PagedList<ModelTv.Result> results) {
                AdapterPageTv adapterPageTv = new AdapterPageTv();
                adapterPageTv.submitList(results);
                fragmentMovieBinding.rvFilm.setAdapter(adapterPageTv);
            }
        });
    }
}
