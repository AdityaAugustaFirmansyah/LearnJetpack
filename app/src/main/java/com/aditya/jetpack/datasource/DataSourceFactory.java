package com.aditya.jetpack.datasource;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.aditya.jetpack.model.ModelMovieView;

public class DataSourceFactory extends PageKeyedDataSource.Factory {

    private ApiInterface apiInterface;
    private Application application;
    private MovieDataSource movieDataSource;
    private MutableLiveData<ModelMovieView> modelMovieViewMutableLiveData;
    private MutableLiveData<MovieDataSource>movieDataSourceMutableLiveData = new MutableLiveData<>();

    public DataSourceFactory(ApiInterface apiInterface, Application application) {
        this.apiInterface = apiInterface;
        this.application = application;
        movieDataSource = new MovieDataSource(apiInterface,application);
        modelMovieViewMutableLiveData = movieDataSource.getModelMovieViewMutableLiveData();
    }

    @NonNull
    @Override
    public DataSource create() {
        movieDataSourceMutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    public MutableLiveData<MovieDataSource> getMovieDataSourceMutableLiveData() {
        return movieDataSourceMutableLiveData;
    }

    public MutableLiveData<ModelMovieView> getModelMovieViewMutableLiveData() {
        return modelMovieViewMutableLiveData;
    }
}
