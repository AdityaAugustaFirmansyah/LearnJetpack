package com.aditya.jetpack;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

public class TvDataSourceFactory extends PageKeyedDataSource.Factory {

    private MutableLiveData<TvDataSource>movieDataSourceMutableLiveData = new MutableLiveData<>();
    private Application application;

    public TvDataSourceFactory(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public DataSource create() {
        TvDataSource tvDataSource = new TvDataSource();
        movieDataSourceMutableLiveData.postValue(tvDataSource);
        return tvDataSource;
    }

    public MutableLiveData<TvDataSource> getMovieDataSourceMutableLiveData() {
        return movieDataSourceMutableLiveData;
    }
}
