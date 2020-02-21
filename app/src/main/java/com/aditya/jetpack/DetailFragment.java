package com.aditya.jetpack;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.aditya.jetpack.databinding.FragmentDetailBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {


    private String TAG = "DetailFragment";

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ModelFilm.Result modelFilm = (ModelFilm.Result) getArguments().getSerializable("TAG_MODEL");
//        Integer modelFilm = getArguments().getInt("TAG_MODEL",0);
        FragmentDetailBinding fragmentDetailBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.fragment_detail,container,false);
        fragmentDetailBinding.setData(modelFilm);
        return fragmentDetailBinding.getRoot();
    }

}
