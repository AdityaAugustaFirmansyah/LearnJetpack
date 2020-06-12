package com.aditya.jetpack.datasource;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.aditya.jetpack.model.ModelMovieView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Long, ModelFilm.Result> {
    private ApiInterface apiInterface;
    private String apiKey = "6f7e6b4fd171ee5a84c759606b18dfa4";
    private ModelMovieView modelMovieViewLive = new ModelMovieView();
    private MutableLiveData<ModelMovieView> modelMovieViewMutableLiveData = new MutableLiveData<>();

    public MovieDataSource(ApiInterface apiInterface, Application application) {
        this.apiInterface = apiInterface;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull final LoadInitialCallback callback) {
        modelMovieViewLive.setStatusLoading(true);
        apiInterface.getModelFilms(apiKey,1).enqueue(new Callback<ModelFilm>() {
            @Override
            public void onResponse(Call<ModelFilm> call, Response<ModelFilm> response) {
                modelMovieViewLive.setStatusLoading(false);
                if (response.isSuccessful()){
                    modelMovieViewLive.setModelFilms(response.body().getResults());
                    callback.onResult(response.body().getResults(),null,(long)2);
                    Log.d("TAG", "onResponse: "+response.body().getResults().size());
                    modelMovieViewMutableLiveData.postValue(modelMovieViewLive);
                }else {
                    modelMovieViewLive.setMsgError("Eroor Code \n"+response.code());
                }
//                modelMovieViewMutableLiveData.postValue(modelMovieViewLive);
            }

            @Override
            public void onFailure(Call<ModelFilm> call, Throwable t) {
                modelMovieViewLive.setStatusLoading(false);
                modelMovieViewLive.setMsgError(t.getLocalizedMessage());
                modelMovieViewMutableLiveData.postValue(modelMovieViewLive);
            }
        });
        modelMovieViewMutableLiveData.postValue(modelMovieViewLive);
    }

    @Override
    public void loadBefore(@NonNull LoadParams params, @NonNull LoadCallback callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, ModelFilm.Result> callback) {
        modelMovieViewLive.setStatusLoading(true);
        apiInterface.getModelFilms(apiKey,params.key).enqueue(new Callback<ModelFilm>() {
            @Override
            public void onResponse(Call<ModelFilm> call, Response<ModelFilm> response) {
                modelMovieViewLive.setStatusLoading(false);
                if (response.isSuccessful()){
                    modelMovieViewLive.setModelFilms(response.body().getResults());
                    callback.onResult(response.body().getResults(),params.key+1);
                    modelMovieViewMutableLiveData.postValue(modelMovieViewLive);
                }else {
                    modelMovieViewLive.setMsgError("Eroor Code \n"+response.code());
                }
            }

            @Override
            public void onFailure(Call<ModelFilm> call, Throwable t) {
                modelMovieViewLive.setStatusLoading(true);
                modelMovieViewLive.setMsgError(t.getLocalizedMessage());
                modelMovieViewMutableLiveData.postValue(modelMovieViewLive);
            }
        });
        modelMovieViewMutableLiveData.postValue(modelMovieViewLive);
    }

    public MutableLiveData<ModelMovieView> getModelMovieViewMutableLiveData() {
        return modelMovieViewMutableLiveData;
    }

    public ModelMovieView getModelMovieViewLive() {
        return modelMovieViewLive;
    }
}