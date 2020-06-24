package com.aditya.jetpack.datasource;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.aditya.jetpack.model.ModeDetailMovieState;
import com.aditya.jetpack.model.ModelDetailTvState;
import com.aditya.jetpack.model.ModelViewTrailerState;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DetailRemoteDataSource {
    private ApiInterface apiInterface;
    private MutableLiveData<ModeDetailMovieState>modeDetailMovieStateMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ModelDetailTvState>modelDetailTvStateMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ModelViewTrailerState> modelViewTrailerStateMutableLiveData = new MutableLiveData<>();

    public DetailRemoteDataSource(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public void getDetailMovie(int movieId){
        modeDetailMovieStateMutableLiveData.postValue(new ModeDetailMovieState(true,null,null));
        apiInterface.getDetailsMovie(movieId,ApiClient.API_KEY).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(modelDetailMovie -> {
            modeDetailMovieStateMutableLiveData.postValue(new ModeDetailMovieState(false,null,modelDetailMovie.body()));
        },throwable -> {
            modeDetailMovieStateMutableLiveData.postValue(new ModeDetailMovieState(false,throwable.getLocalizedMessage(),null));
        });
    }

    public MutableLiveData<ModeDetailMovieState> getModeDetailMovieStateMutableLiveData() {
        return modeDetailMovieStateMutableLiveData;
    }

    public void getTrailer(int movieId){
        modelViewTrailerStateMutableLiveData.postValue(new ModelViewTrailerState(true,null,null));
        apiInterface.getVideoTrailer(movieId,ApiClient.API_KEY).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                modelResponseVideoResponse -> {
                    Log.d("TAG_DETAIL_4", "getTrailer: "+modelResponseVideoResponse.body().getResults().get(0).getKey());
                    modelViewTrailerStateMutableLiveData.postValue(new ModelViewTrailerState(false,null,modelResponseVideoResponse.body().getResults().get(0)));
                },
                throwable -> {
                    Log.d("TAG_DETAIL_1", "getTrailer: "+throwable.getMessage());
                    modelViewTrailerStateMutableLiveData.postValue(new ModelViewTrailerState(false,throwable.getLocalizedMessage(),null));
                });
    }

    public MutableLiveData<ModelViewTrailerState> getModelViewTrailerStateMutableLiveData() {
        return modelViewTrailerStateMutableLiveData;
    }

    public void getDetailTv(int tvId){
        modelDetailTvStateMutableLiveData.postValue(new ModelDetailTvState(true,null,null));
        apiInterface.getDetailsTv(tvId,ApiClient.API_KEY).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(modelDetailTvResponse -> {
            modelDetailTvStateMutableLiveData.postValue(new ModelDetailTvState(false,null,modelDetailTvResponse.body()));
        },throwable -> {
            modelDetailTvStateMutableLiveData.postValue(new ModelDetailTvState(false,throwable.getLocalizedMessage(),null));
        });

        modelViewTrailerStateMutableLiveData.postValue(new ModelViewTrailerState(true,null,null));
        apiInterface.getVideoTrailerTv(tvId,ApiClient.API_KEY).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(modelResponseVideoResponse -> {
            modelViewTrailerStateMutableLiveData.postValue(new ModelViewTrailerState(false,null,modelResponseVideoResponse.body().getResults().get(0)));
        },throwable -> {
            modelViewTrailerStateMutableLiveData.postValue(new ModelViewTrailerState(false,throwable.getLocalizedMessage(),null));
        });
    }

    public MutableLiveData<ModelDetailTvState> getModelDetailTvStateMutableLiveData() {
        return modelDetailTvStateMutableLiveData;
    }
}
