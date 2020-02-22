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
        FragmentDetailBinding fragmentDetailBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.fragment_detail,container,false);
        if (getArguments()!=null&& getArguments().getSerializable("TAG_MODEL") !=null){
            ModelFilm.Result modelFilm = (ModelFilm.Result) getArguments().getSerializable("TAG_MODEL");
//        Integer modelFilm = getArguments().getInt("TAG_MODEL",0);
            fragmentDetailBinding.setData(modelFilm);
            fragmentDetailBinding.setStatusDataFilm(true);
        }else if (getArguments()!=null&& getArguments().getSerializable("TAG_MODEL_1") !=null){
            ModelTv.Result result = (ModelTv.Result) getArguments().getSerializable("TAG_MODEL_1");
            fragmentDetailBinding.setDataTv(result);
            fragmentDetailBinding.setStatusDataFilm(false);
        }

        return fragmentDetailBinding.getRoot();
    }

}
