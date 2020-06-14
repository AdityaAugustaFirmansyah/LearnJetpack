package com.aditya.jetpack.datasource;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

@SuppressWarnings("rawtypes")
public class DataSourceFactory extends PageKeyedDataSource.Factory {

    private ApiInterface apiInterface;
    private MutableLiveData<MovieDataSource>movieDataSourceMutableLiveData = new MutableLiveData<>();

    public DataSourceFactory(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    @SuppressWarnings("rawtypes")
    @NonNull
    @Override
    public DataSource create() {
        MovieDataSource movieDataSource = new MovieDataSource(apiInterface);
        movieDataSourceMutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

}
