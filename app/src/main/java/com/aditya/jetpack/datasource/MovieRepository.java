package com.aditya.jetpack.datasource;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private static final String TAG = "TAG_REPOSITORY";
    ApiInterface apiInterface;

    private MutableLiveData<String>message = new MutableLiveData<>();
    private MutableLiveData<ArrayList<ModelFilm.Result>>mutableLiveDataResult = new MutableLiveData<>();
    private MutableLiveData<Boolean>statusLoading = new MutableLiveData<>();

    private MutableLiveData<ArrayList<ModelTv.Result>>mutableLiveDataResultTv = new MutableLiveData<>();

    public MovieRepository() {
        this.apiInterface = ApiClient.apiInterface;
    }
    public void getListMovie(){
        HashMap<String,String>map = new HashMap<>();
        map.put("api_key","6f7e6b4fd171ee5a84c759606b18dfa4");
        statusLoading.postValue(true);
        apiInterface.getModelFilms(map).enqueue(new Callback<ModelFilm>() {
            @Override
            public void onResponse(Call<ModelFilm> call, Response<ModelFilm> response) {
                statusLoading.postValue(false);
                if (response.isSuccessful()){
                    mutableLiveDataResult.setValue(response.body().getResults());
                }else{
                   message.postValue("gagal");
                   mutableLiveDataResult.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ModelFilm> call, Throwable t) {
                statusLoading.postValue(false);
                mutableLiveDataResult.setValue(null);
                message.postValue(t.getMessage());
            }
        });
    }

    public void getListTv(){
        HashMap<String,String>map = new HashMap<>();
        map.put("api_key","6f7e6b4fd171ee5a84c759606b18dfa4");
        apiInterface.getModelTvs(map).enqueue(new Callback<ModelTv>() {
            @Override
            public void onResponse(Call<ModelTv> call, Response<ModelTv> response) {
                if (response.isSuccessful()){
                    mutableLiveDataResultTv.setValue(response.body().getResults());
                }else {
                    message.setValue(response.message());
                    mutableLiveDataResultTv.setValue(null);
                    Log.d(TAG, "onResponse: "+response.message());
                }
            }

            @Override
            public void onFailure(Call<ModelTv> call, Throwable t) {
                message.setValue(t.getMessage());
                mutableLiveDataResultTv.setValue(null);
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    public MutableLiveData<String> getMessage() {
        return message;
    }

    public MutableLiveData<ArrayList<ModelFilm.Result>> getMutableLiveDataResult() {
        return mutableLiveDataResult;
    }

    public MutableLiveData<Boolean> getStatusLoading() {
        return statusLoading;
    }

    public MutableLiveData<ArrayList<ModelTv.Result>> getMutableLiveDataResultTv() {
        return mutableLiveDataResultTv;
    }
}
