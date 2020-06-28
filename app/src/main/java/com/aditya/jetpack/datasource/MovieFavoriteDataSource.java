package com.aditya.jetpack.datasource;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.aditya.jetpack.dao.DaoMovie;
import com.aditya.jetpack.helper.AppDatabase;
import com.aditya.jetpack.model.ModelFilm;

import java.util.List;

public class MovieFavoriteDataSource {

    private DaoMovie daoMovie;
    private LiveData<List<ModelFilm.Result>> allResultsFavoriteMovie;

    public MovieFavoriteDataSource(Context application){
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        daoMovie = appDatabase.filmResultDao();
        allResultsFavoriteMovie = daoMovie.getAll();
    }

    public LiveData<List<ModelFilm.Result>> getAllResultsFavoriteMovie() {
        return allResultsFavoriteMovie;
    }

    public void insertFavoriteMovie(ModelFilm.Result result){
        daoMovie.insertAll(result);
    }
}
