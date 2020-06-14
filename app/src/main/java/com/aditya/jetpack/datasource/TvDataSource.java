package com.aditya.jetpack.datasource;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvDataSource extends PageKeyedDataSource<Long, ModelTv.Result> {

    private ApiInterface apiInterface;
    public TvDataSource() {
        this.apiInterface = ApiClient.apiInterface;
    }

    @Override
    public void loadInitial(@NonNull final LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, ModelTv.Result> callback) {
        apiInterface.getModelTv(ApiClient.API_KEY,1).enqueue(new Callback<ModelTv>() {
            @Override
            public void onResponse(@NonNull Call<ModelTv> call, @NonNull Response<ModelTv> response) {
                assert response.body() != null;
                callback.onResult(response.body().getResults(),null,(long)2);
            }

            @Override
            public void onFailure(@NonNull Call<ModelTv> call, @NonNull Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, ModelTv.Result> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, ModelTv.Result> callback) {
        apiInterface.getModelTv(ApiClient.API_KEY,params.key).enqueue(new Callback<ModelTv>() {
            @Override
            public void onResponse(@NonNull Call<ModelTv> call, @NonNull Response<ModelTv> response) {
                callback.onResult(Objects.requireNonNull(response.body()).getResults(),params.key+1);
            }

            @Override
            public void onFailure(@NonNull Call<ModelTv> call, @NonNull Throwable t) {

            }
        });
    }
}
