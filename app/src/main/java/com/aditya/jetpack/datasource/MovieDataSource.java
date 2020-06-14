package com.aditya.jetpack.datasource;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("unchecked")
public class MovieDataSource extends PageKeyedDataSource<Long, ModelFilm.Result> {
    private ApiInterface apiInterface;
    private String apiKey = "6f7e6b4fd171ee5a84c759606b18dfa4";

    public MovieDataSource(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull final LoadInitialCallback callback) {
        apiInterface.getModelFilms(apiKey,1).enqueue(new Callback<ModelFilm>() {
            @Override
            public void onResponse(@NonNull Call<ModelFilm> call, @NonNull Response<ModelFilm> response) {
                assert response.body() != null;
                callback.onResult(response.body().getResults(),null,(long)2);
                Log.d("TAG_MOVIE_DATA_SOURCE", "onResponse: "+response.body().getResults().size());
            }

            @Override
            public void onFailure(@NonNull Call<ModelFilm> call, @NonNull Throwable t) {
                Log.e("TAG_MOVIE_DATA_SOURCE", "onResponse: "+t.getLocalizedMessage());
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
            public void onResponse(@NonNull Call<ModelFilm> call, @NonNull Response<ModelFilm> response) {
                assert response.body() != null;
                callback.onResult(response.body().getResults(),params.key+1);
                Log.d("TAG_MOVIE_DATA_SOURCE", "onResponse: "+response.body().getResults().size());
            }

            @Override
            public void onFailure(@NonNull Call<ModelFilm> call, @NonNull Throwable t) {
                Log.e("TAG_MOVIE_DATA_SOURCE", "onResponse: "+t.getLocalizedMessage());
            }
        });
    }
}