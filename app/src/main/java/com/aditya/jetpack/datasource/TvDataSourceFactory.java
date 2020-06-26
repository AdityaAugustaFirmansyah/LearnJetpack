package com.aditya.jetpack.datasource;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.aditya.jetpack.api.ApiInterface;

import io.reactivex.disposables.CompositeDisposable;

@SuppressWarnings("rawtypes")
public class TvDataSourceFactory extends PageKeyedDataSource.Factory {

    private MutableLiveData<TvDataSource>movieDataSourceMutableLiveData = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable;
    private ApiInterface apiInterface;

    public TvDataSourceFactory(CompositeDisposable compositeDisposable, ApiInterface apiInterface) {
        this.compositeDisposable = compositeDisposable;
        this.apiInterface = apiInterface;
    }

    @NonNull
    @Override
    public DataSource create() {
        TvDataSource tvDataSource = new TvDataSource(apiInterface,compositeDisposable);
        movieDataSourceMutableLiveData.postValue(tvDataSource);
        return tvDataSource;
    }

    public MutableLiveData<TvDataSource> getMovieDataSourceMutableLiveData() {
        return movieDataSourceMutableLiveData;
    }
}
