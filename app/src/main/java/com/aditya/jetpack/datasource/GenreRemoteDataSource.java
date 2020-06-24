package com.aditya.jetpack.datasource;

import androidx.lifecycle.MutableLiveData;

import com.aditya.jetpack.model.ModelGenre;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenreRemoteDataSource {
    private ApiInterface apiInterface;
    public static final String TAG_GENRE_MOVIE = "TAG_GENRE_MOVIE";
    public static final String TAG_GENRE_TV = "TAG_GENRE_TV";
    private MutableLiveData<ArrayList<ModelGenre>>modelGenresMovieMutableLiveData= new MutableLiveData<>();
    private MutableLiveData<ArrayList<ModelGenre>>modelGenresTvMutableLiveData= new MutableLiveData<>();

    public GenreRemoteDataSource(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public void getGenreMovie(){

        apiInterface.getGenreMovie(ApiClient.API_KEY).enqueue(new Callback<ModelGenre.ModelGenreResponse>() {
            @Override
            public void onResponse(Call<ModelGenre.ModelGenreResponse> call, Response<ModelGenre.ModelGenreResponse> response) {
                modelGenresMovieMutableLiveData.postValue(response.body().getGenres());
            }

            @Override
            public void onFailure(Call<ModelGenre.ModelGenreResponse> call, Throwable t) {

            }
        });
    }

    public void getGenreTv(){
        apiInterface.getGenreTv(ApiClient.API_KEY).enqueue(new Callback<ModelGenre.ModelGenreResponse>() {
            @Override
            public void onResponse(Call<ModelGenre.ModelGenreResponse> call, Response<ModelGenre.ModelGenreResponse> response) {
                modelGenresTvMutableLiveData.postValue(response.body().getGenres());
            }

            @Override
            public void onFailure(Call<ModelGenre.ModelGenreResponse> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<ArrayList<ModelGenre>> getModelGenresMovieMutableLiveData() {
        return modelGenresMovieMutableLiveData;
    }

    public MutableLiveData<ArrayList<ModelGenre>> getModelGenresTvMutableLiveData() {
        return modelGenresTvMutableLiveData;
    }
}
