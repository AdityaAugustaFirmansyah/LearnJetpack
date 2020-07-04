package com.aditya.jetpack.datasource;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;

import com.aditya.jetpack.db.DaoMovie;
import com.aditya.jetpack.db.AppDatabase;
import com.aditya.jetpack.model.ModelFilm;
import com.aditya.jetpack.model.ModelTv;

public class MovieFavoriteDataSource {

    private DaoMovie daoMovie;
    private DataSource.Factory<Integer,ModelFilm.Result> allResultsFavoriteMovie;
    private DataSource.Factory<Integer, ModelTv.Result>allResultsFavoriteTvs;

    public MovieFavoriteDataSource(Context application){
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        daoMovie = appDatabase.filmResultDao();
        allResultsFavoriteMovie = daoMovie.getAll();
        allResultsFavoriteTvs = daoMovie.getAllTvs();
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

    public DataSource.Factory<Integer, ModelTv.Result> getAllResultsFavoriteTvs() {
        return allResultsFavoriteTvs;
    }

    public void addFavoriteTv(ModelTv.Result... results){
        AsyncTask.execute(() -> daoMovie.insertAllTvs(results));
    }

    public LiveData<ModelTv.Result> getTvFavorite(int id){
        return daoMovie.getTvFavorite(id);
    }

    public void deleteFavoriteTv(ModelTv.Result... result){
        AsyncTask.execute(()->daoMovie.deleteTv(result));
    }
}
