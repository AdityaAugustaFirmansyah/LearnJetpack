package com.aditya.jetpack.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.aditya.jetpack.api.ApiClient;
import com.aditya.jetpack.api.ApiInterface;
import com.aditya.jetpack.datasource.DetailRemoteDataSource;
import com.aditya.jetpack.model.ModeDetailMovieState;
import com.aditya.jetpack.model.ModelDetailTvState;
import com.aditya.jetpack.model.ModelViewTrailerState;

public class ViewModelDetailMovie extends ViewModel {
    private LiveData<ModeDetailMovieState> modeDetailMovieStateMutableLiveData;
    private LiveData<ModelViewTrailerState>modelViewTrailerStateLiveData;
    private LiveData<ModelDetailTvState>modelDetailTvStateLiveData;
    private DetailRemoteDataSource detailRemoteDataSource;

    public ViewModelDetailMovie() {
        ApiInterface apiInterface = ApiClient.apiInterface;
        detailRemoteDataSource = new DetailRemoteDataSource(apiInterface);
        modeDetailMovieStateMutableLiveData = detailRemoteDataSource.getModeDetailMovieStateMutableLiveData();
        modelViewTrailerStateLiveData = detailRemoteDataSource.getModelViewTrailerStateMutableLiveData();
        modelDetailTvStateLiveData = detailRemoteDataSource.getModelDetailTvStateMutableLiveData();
    }

    public void getDetailMovie(int movieId){
        detailRemoteDataSource.getDetailMovie(movieId);
    }

    public void getTrailer(int movieId){
        detailRemoteDataSource.getTrailer(movieId);
    }

    public void getDetailTv(int tvId){
        detailRemoteDataSource.getDetailTv(tvId);
    }

    public LiveData<ModeDetailMovieState> getModeDetailMovieStateMutableLiveData() {
        return modeDetailMovieStateMutableLiveData;
    }

    public LiveData<ModelViewTrailerState> getModelViewTrailerStateLiveData() {
        return modelViewTrailerStateLiveData;
    }

    public LiveData<ModelDetailTvState> getModelDetailTvStateLiveData() {
        return modelDetailTvStateLiveData;
    }
}
