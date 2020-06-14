package com.aditya.jetpack.datasource;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

@SuppressWarnings("rawtypes")
public class TvDataSourceFactory extends PageKeyedDataSource.Factory {

    private MutableLiveData<TvDataSource>movieDataSourceMutableLiveData = new MutableLiveData<>();

    @NonNull
    @Override
    public DataSource create() {
        TvDataSource tvDataSource = new TvDataSource();
        movieDataSourceMutableLiveData.postValue(tvDataSource);
        return tvDataSource;
    }

}
