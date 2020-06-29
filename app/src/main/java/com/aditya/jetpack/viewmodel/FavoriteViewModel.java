package com.aditya.jetpack.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.aditya.jetpack.datasource.MovieFavoriteDataSource;
import com.aditya.jetpack.model.ModelFilm;

public class FavoriteViewModel extends AndroidViewModel {

    private MovieFavoriteDataSource movieFavoriteDataSource;
    private PagedList.Config config;
    private LiveData<PagedList<ModelFilm.Result>>allResults;

    public FavoriteViewModel(@NonNull Application application) {
        super(application);
        movieFavoriteDataSource = new MovieFavoriteDataSource(application);
        config = new PagedList.Config.Builder().setEnablePlaceholders(false)
                .setPageSize(1)
                .setInitialLoadSizeHint(2)
                .build();
        allResults = new LivePagedListBuilder<>(movieFavoriteDataSource.getAllResultsFavoriteMovie(), config).build();
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
}
