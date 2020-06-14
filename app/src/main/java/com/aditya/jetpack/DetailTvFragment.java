package com.aditya.jetpack;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.aditya.jetpack.databinding.FragmentDetailTvBinding;
import com.aditya.jetpack.datasource.ModelTv;

public class DetailTvFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentDetailTvBinding detailTvBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail_tv,container,false);
        ModelTv.Result result = (ModelTv.Result) (getArguments() != null ? getArguments().getSerializable("TAG_MODEL_1") : null);
        detailTvBinding.setData(result);
        return detailTvBinding.getRoot();
    }

}
