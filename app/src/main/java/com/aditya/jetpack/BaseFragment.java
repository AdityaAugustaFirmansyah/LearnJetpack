package com.aditya.jetpack;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.aditya.jetpack.databinding.FragmentBaseBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {


    private String TAG = "BaseFragment";
    FragmentBaseBinding fragmentBaseBinding;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentBaseBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.fragment_base,container,false);
        return fragmentBaseBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentBaseBinding.bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.item_film){
                    getChildFragmentManager().beginTransaction().replace(fragmentBaseBinding.containerFrame.getId(),new MovieFragment()).commit();
                    Log.d(TAG, "onNavigationItemSelected: ");
                }else {
                    getChildFragmentManager().beginTransaction().replace(fragmentBaseBinding.containerFrame.getId(),new TvFragment()).commit();
                }
                return true;
            }
        });
    }
}
