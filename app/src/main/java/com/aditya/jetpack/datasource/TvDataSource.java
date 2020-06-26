package com.aditya.jetpack.datasource;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.aditya.jetpack.api.ApiClient;
import com.aditya.jetpack.api.ApiInterface;
import com.aditya.jetpack.model.ModelTv;
import com.aditya.jetpack.model.ModelTvView;

import io.reactivex.disposables.CompositeDisposable;

public class TvDataSource extends PageKeyedDataSource<Long, ModelTv.Result> {

    private ApiInterface apiInterface;
    private CompositeDisposable compositeDisposable;
    private MutableLiveData<ModelTvView> loadInitial = new MutableLiveData<>();
    private MutableLiveData<ModelTvView> networkState = new MutableLiveData<>();

    public TvDataSource(ApiInterface apiInterface, CompositeDisposable compositeDisposable) {
        this.apiInterface = apiInterface;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void loadInitial(@NonNull final LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, ModelTv.Result> callback) {
        networkState.postValue(new ModelTvView(true,null));
        loadInitial.postValue(new ModelTvView(true,null));
        compositeDisposable.add(apiInterface.getModelTv(ApiClient.API_KEY,(long)1).subscribe(modelTv -> {
            loadInitial.postValue(new ModelTvView(false,null));
            networkState.postValue(new ModelTvView(false,null));
            callback.onResult(modelTv.getResults(),null,(long)2);
        },throwable -> {
            loadInitial.postValue(new ModelTvView(false,throwable.getLocalizedMessage()));
            networkState.postValue(new ModelTvView(false,throwable.getLocalizedMessage()));
        }));
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, ModelTv.Result> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, ModelTv.Result> callback) {
        networkState.postValue(new ModelTvView(true,null));
        compositeDisposable.add(apiInterface.getModelTv(ApiClient.API_KEY,params.key).subscribe(modelTv -> {
            networkState.postValue(new ModelTvView(false,null));
            callback.onResult(modelTv.getResults(),(long)params.key+1);
        },throwable -> networkState.postValue(new ModelTvView(true,throwable.getLocalizedMessage()))));
    }

    public MutableLiveData<ModelTvView> getLoadInitial() {
        return loadInitial;
    }

    public MutableLiveData<ModelTvView> getNetworkState() {
        return networkState;
    }
}
