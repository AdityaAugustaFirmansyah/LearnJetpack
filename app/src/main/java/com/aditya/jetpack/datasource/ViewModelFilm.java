package com.aditya.jetpack.datasource;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class ViewModelFilm extends ViewModel {
    static MovieRepository movieRepository = new MovieRepository();
    MutableLiveData<ArrayList<ModelFilm.Result>>mutableLiveDataResults = movieRepository.getMutableLiveDataResult();
    MutableLiveData<String>mutableLiveDataMessage = movieRepository.getMessage();
    MutableLiveData<Boolean>mutableLiveDataStatusLoading = movieRepository.getStatusLoading();
    MutableLiveData<ArrayList<ModelTv.Result>>mutableLiveDataResultsTv = movieRepository.getMutableLiveDataResultTv();
    public static void onGetListFilm(){
        movieRepository.getListMovie();
    }
    public static void  onGetListTv(){
        movieRepository.getListTv();
    }
}
