package com.aditya.jetpack;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Long,ModelFilm.Result> {
    private ApiInterface apiInterface;
    private Application application;
    private String apiKey = "6f7e6b4fd171ee5a84c759606b18dfa4";
    public MovieDataSource(ApiInterface apiInterface, Application application) {
        this.apiInterface = apiInterface;
        this.application = application;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull final LoadInitialCallback callback) {
        apiInterface.getModelFilms(apiKey,1).enqueue(new Callback<ModelFilm>() {
            @Override
            public void onResponse(Call<ModelFilm> call, Response<ModelFilm> response) {
                callback.onResult(response.body().getResults(),null,(long)2);
            }

            @Override
            public void onFailure(Call<ModelFilm> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams params, @NonNull LoadCallback callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, ModelFilm.Result> callback) {
        apiInterface.getModelFilms(apiKey,params.key).enqueue(new Callback<ModelFilm>() {
            @Override
            public void onResponse(Call<ModelFilm> call, Response<ModelFilm> response) {
                callback.onResult(response.body().getResults(),params.key+1);
            }

            @Override
            public void onFailure(Call<ModelFilm> call, Throwable t) {

            }
        });
    }
}