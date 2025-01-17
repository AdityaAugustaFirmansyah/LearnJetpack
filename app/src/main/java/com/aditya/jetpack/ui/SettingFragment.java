package com.aditya.jetpack.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.aditya.jetpack.R;
import com.aditya.jetpack.databinding.FragmentSettingBinding;
import com.aditya.jetpack.helper.TinyDB;

import java.util.Objects;

public class SettingFragment extends Fragment {

    private FragmentSettingBinding fragmentSettingBinding;
    private TinyDB tinyDB;
    public static final String TAG_SWITCH_DARK = "TAG_SWITCH_DARK";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentSettingBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.fragment_setting,container,false);
        tinyDB = new TinyDB(getContext());
        return fragmentSettingBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentSettingBinding.switch1.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b){
                fragmentSettingBinding.switch1.setText("On");
            }else {
                fragmentSettingBinding.switch1.setText("Off");
            }
        });
        fragmentSettingBinding.switch1.setChecked(tinyDB.getBoolean(TAG_SWITCH_DARK));
        fragmentSettingBinding.switch1.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b){
                fragmentSettingBinding.switch1.setText("On");
                tinyDB.putBoolean(TAG_SWITCH_DARK, true);
                Intent intent = Objects.requireNonNull(getActivity()).getIntent();
                getActivity().finish();
                getActivity().startActivity(intent);
            }else {
                fragmentSettingBinding.switch1.setText("Off");
                tinyDB.putBoolean(TAG_SWITCH_DARK, false);
                Intent intent = Objects.requireNonNull(getActivity()).getIntent();
                getActivity().finish();
                getActivity().startActivity(intent);
            }
        });
    }
}