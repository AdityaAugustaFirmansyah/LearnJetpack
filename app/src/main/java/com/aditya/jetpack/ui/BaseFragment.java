package com.aditya.jetpack.ui;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.aditya.jetpack.R;
import com.aditya.jetpack.databinding.FragmentBaseBinding;
import com.aditya.jetpack.helper.BottomNavigationBehavior;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {


    FragmentBaseBinding fragmentBaseBinding;
    private static final String TAG_FRAGMENT_FILM = "TAG_FRAGMENT_FILM";
    private BaseFilmFragment baseFilmFragment = new BaseFilmFragment();
    private BaseFavoriteFragment baseFavoriteFragment = new BaseFavoriteFragment();
    private static final String TAG_FRAGMENT_FAVORITE = "TAG_FRAGMENT_FAVORITE";
    private String activeFragment =TAG_FRAGMENT_FILM;
    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentBaseBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_base,container,false);
        return fragmentBaseBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState!=null){
            if (Objects.equals(savedInstanceState.getString("TAG_FRAGMENT"), TAG_FRAGMENT_FILM)){
                activeFragment = TAG_FRAGMENT_FILM;
            }else {
                activeFragment = TAG_FRAGMENT_FAVORITE;
            }
        }else {
            Log.d("TAG_POSITION_FRAGMENT", "onActivityCreated: "+(baseFilmFragment !=null&&!baseFilmFragment.isInLayout())+" "+!baseFavoriteFragment.isInLayout());
            if (baseFilmFragment !=null&&!baseFilmFragment.isInLayout()&& activeFragment.equals(TAG_FRAGMENT_FILM)){
                getChildFragmentManager().beginTransaction().replace(fragmentBaseBinding.containerFrame.getId(),new BaseFilmFragment(),TAG_FRAGMENT_FILM).commit();
                activeFragment = TAG_FRAGMENT_FILM;
            }else if(baseFavoriteFragment !=null&&!baseFavoriteFragment.isInLayout()&&activeFragment.equals(TAG_FRAGMENT_FAVORITE)){
                getChildFragmentManager().beginTransaction().replace(fragmentBaseBinding.containerFrame.getId(),new BaseFavoriteFragment(), TAG_FRAGMENT_FAVORITE).commit();
                activeFragment = TAG_FRAGMENT_FAVORITE;
            }
        }

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) fragmentBaseBinding.bottomNavigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

        fragmentBaseBinding.bottomNavigation.setOnNavigationItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.item_film){
                getChildFragmentManager().beginTransaction().replace(fragmentBaseBinding.containerFrame.getId(),new BaseFilmFragment(),TAG_FRAGMENT_FILM).commit();
                activeFragment = TAG_FRAGMENT_FILM;
            }else {
                getChildFragmentManager().beginTransaction().replace(fragmentBaseBinding.containerFrame.getId(),new BaseFavoriteFragment(), TAG_FRAGMENT_FAVORITE).commit();
                activeFragment = TAG_FRAGMENT_FAVORITE;
            }
            return true;
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("TAG_FRAGMENT",activeFragment);
        super.onSaveInstanceState(outState);
    }
}
