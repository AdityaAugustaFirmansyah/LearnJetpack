package com.aditya.jetpack;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

public class DataSourceFactory extends PageKeyedDataSource.Factory {

    private ApiInterface apiInterface;
    private Application application;
    private MovieDataSource movieDataSource;
    private MutableLiveData<MovieDataSource>movieDataSourceMutableLiveData = new MutableLiveData<>();

    public DataSourceFactory(ApiInterface apiInterface, Application application) {
        this.apiInterface = apiInterface;
        this.application = application;
    }

    @NonNull
    @Override
    public DataSource create() {
        movieDataSource = new MovieDataSource(apiInterface,application);
        movieDataSourceMutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    public MutableLiveData<MovieDataSource> getMovieDataSourceMutableLiveData() {
        return movieDataSourceMutableLiveData;
    }
}
