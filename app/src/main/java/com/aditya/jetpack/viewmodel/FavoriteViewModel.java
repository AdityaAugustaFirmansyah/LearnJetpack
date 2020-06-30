package com.aditya.jetpack.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.aditya.jetpack.datasource.MovieFavoriteDataSource;
import com.aditya.jetpack.model.ModelFilm;
import com.aditya.jetpack.model.ModelTv;

public class FavoriteViewModel extends AndroidViewModel {

    private MovieFavoriteDataSource movieFavoriteDataSource;
    private PagedList.Config config;
    private LiveData<PagedList<ModelFilm.Result>>allResults;
    private LiveData<PagedList<ModelTv.Result>>allResultsTvs;

    public FavoriteViewModel(@NonNull Application application) {
        super(application);
        movieFavoriteDataSource = new MovieFavoriteDataSource(application);
        config = new PagedList.Config.Builder().setEnablePlaceholders(false)
                .setPageSize(1)
                .setInitialLoadSizeHint(2)
                .build();
        allResults = new LivePagedListBuilder<>(movieFavoriteDataSource.getAllResultsFavoriteMovie(), config).build();
        allResultsTvs = new LivePagedListBuilder<>(movieFavoriteDataSource.getAllResultsFavoriteTvs(),config).build();
    }

    public void insertFavoriteMovie(ModelFilm.Result result){
        movieFavoriteDataSource.insertFavoriteMovie(result);
    }

    public LiveData<PagedList<ModelFilm.Result>> getAllResults() {
        return allResults;
    }

    public LiveData<ModelFilm.Result> getResultLiveData(int id) {
        return movieFavoriteDataSource.getResultLiveData(id);
    }

    public void deleteFavoriteMovie(ModelFilm.Result result){
        movieFavoriteDataSource.deleteFavoriteMovie(result);
    }

    public LiveData<PagedList<ModelTv.Result>> getAllResultsTvs() {
        return allResultsTvs;
    }

    public void insertFavoriteTvs(ModelTv.Result result){
        movieFavoriteDataSource.addFavoriteTv(result);
    }

    public void deleteFavoriteTv(ModelTv.Result result){
        movieFavoriteDataSource.deleteFavoriteTv(result);
    }

    public LiveData<ModelTv.Result>getTvResults(int id){
        return movieFavoriteDataSource.getTvFavorite(id);
    }
}
