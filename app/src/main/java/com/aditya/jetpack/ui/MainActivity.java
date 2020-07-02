package com.aditya.jetpack.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.aditya.jetpack.R;
import com.aditya.jetpack.helper.TinyDB;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TinyDB tinyDB = new TinyDB(getApplicationContext());
        if (tinyDB.getBoolean(SettingFragment.TAG_SWITCH_DARK)){
            setTheme(R.style.AppThemeDark);
        }else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}