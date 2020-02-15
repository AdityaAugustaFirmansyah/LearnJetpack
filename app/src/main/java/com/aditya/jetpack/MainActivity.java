package com.aditya.jetpack;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.aditya.jetpack.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private String TAG = "TAG_MAIN_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        activityMainBinding.bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.item_film){
                    getSupportFragmentManager().beginTransaction().replace(activityMainBinding.containerFrame.getId(),new MovieFragment()).commit();
                    Log.d(TAG, "onNavigationItemSelected: ");
                }else {
                    getSupportFragmentManager().beginTransaction().replace(activityMainBinding.containerFrame.getId(),new TvFragment()).commit();
                }
                return true;
            }
        });
    }
}