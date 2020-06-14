package com.aditya.jetpack;


import android.os.Bundle;
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

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {


    FragmentBaseBinding fragmentBaseBinding;
    private static final String TAG_FRAGMENT_FILM = "TAG_FRAGMENT_FILM";
    private MovieFragment movieFragment = new MovieFragment();
    private TvFragment tvFragment = new TvFragment();
    private static final String TAG_FRAGMENT_TV = "TAG_FRAGMENT_TV";
    private String activeFragment ;
    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentBaseBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.fragment_base,container,false);
        return fragmentBaseBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState!=null){
            if (Objects.equals(savedInstanceState.getString("TAG_FRAGMENT"), TAG_FRAGMENT_FILM)){
                movieFragment = (MovieFragment) getChildFragmentManager().findFragmentByTag(TAG_FRAGMENT_FILM);
                activeFragment = TAG_FRAGMENT_FILM;
            }else {
                tvFragment = (TvFragment) getChildFragmentManager().findFragmentByTag(TAG_FRAGMENT_TV);
                activeFragment = TAG_FRAGMENT_TV;
            }
        }else {
            if (movieFragment!=null&&!movieFragment.isInLayout()){
                getChildFragmentManager().beginTransaction().replace(fragmentBaseBinding.containerFrame.getId(),movieFragment,TAG_FRAGMENT_FILM).commit();
                activeFragment = TAG_FRAGMENT_FILM;
            }else if(tvFragment!=null&&!tvFragment.isInLayout()){
                getChildFragmentManager().beginTransaction().replace(fragmentBaseBinding.containerFrame.getId(),tvFragment,TAG_FRAGMENT_TV).commit();
                activeFragment = TAG_FRAGMENT_TV;
            }
        }

        fragmentBaseBinding.bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.item_film){
                    getChildFragmentManager().beginTransaction().replace(fragmentBaseBinding.containerFrame.getId(),movieFragment,TAG_FRAGMENT_FILM).commit();
                    activeFragment = TAG_FRAGMENT_FILM;
                }else {
                    getChildFragmentManager().beginTransaction().replace(fragmentBaseBinding.containerFrame.getId(),tvFragment,TAG_FRAGMENT_TV).commit();
                    activeFragment = TAG_FRAGMENT_TV;
                }
                return true;
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("TAG_FRAGMENT",activeFragment);
        super.onSaveInstanceState(outState);
    }
}
