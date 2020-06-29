package com.aditya.jetpack.datasource;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;

import com.aditya.jetpack.dao.DaoMovie;
import com.aditya.jetpack.helper.AppDatabase;
import com.aditya.jetpack.model.ModelFilm;

public class MovieFavoriteDataSource {

    private DaoMovie daoMovie;
    private DataSource.Factory<Integer,ModelFilm.Result> allResultsFavoriteMovie;

    public MovieFavoriteDataSource(Context application){
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        daoMovie = appDatabase.filmResultDao();
        allResultsFavoriteMovie = daoMovie.getAll();
    }

    public DataSource.Factory<Integer,ModelFilm.Result> getAllResultsFavoriteMovie() {
        return allResultsFavoriteMovie;
    }

    public void insertFavoriteMovie(ModelFilm.Result result){
        AsyncTask.execute(() -> daoMovie.insertAll(result));
    }

    public void deleteFavoriteMovie(ModelFilm.Result result){
        AsyncTask.execute(() -> daoMovie.deleteMovieFavorite(result));
    }

    public LiveData<ModelFilm.Result> getResultLiveData(int id) {
        return daoMovie.getMovieFavorite(id);
    }
}
