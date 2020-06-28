package com.aditya.jetpack.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.aditya.jetpack.datasource.MovieFavoriteDataSource;
import com.aditya.jetpack.model.ModelFilm;

import java.util.List;

public class FavoriteViewModel extends AndroidViewModel {

    private MovieFavoriteDataSource movieFavoriteDataSource;
    private LiveData<List<ModelFilm.Result>>allResults;
    public FavoriteViewModel(@NonNull Application application) {
        super(application);
        movieFavoriteDataSource = new MovieFavoriteDataSource(application);
        allResults = movieFavoriteDataSource.getAllResultsFavoriteMovie();
    }

    public void insertFavoriteMovie(ModelFilm.Result result){
        movieFavoriteDataSource.insertFavoriteMovie(result);
    }

    public LiveData<List<ModelFilm.Result>> getAllResults() {
        return allResults;
    }
}
