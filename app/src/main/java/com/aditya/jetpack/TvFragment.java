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

import com.aditya.jetpack.databinding.FragmentMovieBinding;

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
//        if (ViewModelFilm.movieRepository.getMutableLiveDataResultTv().getValue() == null){
//            Log.d(TAG, "onCreateView: ");
//            ViewModelFilm.onGetListTv();
//        }
        fragmentMovieBinding.rvFilm.setLayoutManager(new LinearLayoutManager(view.getContext()));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        ViewModelFilm viewModelFilm = ViewModelProviders.of(getActivity()).get(ViewModelFilm.class);
//        viewModelFilm.mutableLiveDataResultsTv.observe(getViewLifecycleOwner(), new Observer<ArrayList<ModelTv.Result>>() {
//            @Override
//            public void onChanged(ArrayList<ModelTv.Result> results) {
//                if (results!=null){
//                    Log.d(TAG, "onChanged: ");
//                    AdapterRv adapterRv = new AdapterRv();
//                    adapterRv.setModelTvs(results);
//                    fragmentMovieBinding.rvFilm.setAdapter(adapterRv);
//                }else {
//                    Log.d(TAG, "onChanged: null");
//                }
//            }
//        });

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
