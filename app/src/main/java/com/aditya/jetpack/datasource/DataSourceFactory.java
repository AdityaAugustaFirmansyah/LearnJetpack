package com.aditya.jetpack.datasource;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import io.reactivex.disposables.CompositeDisposable;

@SuppressWarnings("rawtypes")
public class DataSourceFactory extends PageKeyedDataSource.Factory {

    private ApiInterface apiInterface;
    private CompositeDisposable compositeDisposable;
    private MutableLiveData<MovieDataSource>movieDataSourceMutableLiveData = new MutableLiveData<>();

    public DataSourceFactory(ApiInterface apiInterface, CompositeDisposable compositeDisposable) {
        this.apiInterface = apiInterface;
        this.compositeDisposable = compositeDisposable;
    }

    @SuppressWarnings("rawtypes")
    @NonNull
    @Override
    public DataSource create() {
        MovieDataSource movieDataSource = new MovieDataSource(apiInterface,compositeDisposable);
        movieDataSourceMutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    public MutableLiveData<MovieDataSource> getMovieDataSourceMutableLiveData() {
        return movieDataSourceMutableLiveData;
    }
}
