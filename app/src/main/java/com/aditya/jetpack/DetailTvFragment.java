package com.aditya.jetpack;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.aditya.jetpack.databinding.FragmentDetailTvBinding;
import com.aditya.jetpack.datasource.ModelTv;

public class DetailTvFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentDetailTvBinding detailTvBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail_tv,container,false);
        ModelTv.Result result = (ModelTv.Result) getArguments().getSerializable("TAG_MODEL_1");
        detailTvBinding.setData(result);
        return detailTvBinding.getRoot();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
