package com.aditya.jetpack.datasource;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.aditya.jetpack.model.ModelGenre;
import com.aditya.jetpack.model.ModelMovieView;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;

@SuppressWarnings("unchecked")
public class MovieViewModel extends AndroidViewModel {

    private DataSourceFactory dataSourceFactory;
    private PagedList.Config config;
    private LiveData<PagedList<ModelFilm.Result>>listLiveDataPage;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private GenreRemoteDataSource genreRemoteDataSource;

    public MovieViewModel(@NonNull Application application) {
        super(application);

        ApiInterface apiInterface = ApiClient.apiInterface;
        genreRemoteDataSource= new GenreRemoteDataSource(apiInterface);
        genreRemoteDataSource.getGenreMovie();
        dataSourceFactory = new DataSourceFactory(apiInterface, compositeDisposable);
        config = new PagedList.Config.Builder()
                .setPageSize(1)
                .setInitialLoadSizeHint(2)
                .setEnablePlaceholders(false)
                .build();
        listLiveDataPage = new LivePagedListBuilder(dataSourceFactory,config).build();
    }

    public LiveData<PagedList<ModelFilm.Result>> getListLiveDataPage() {
        return listLiveDataPage;
    }

    public LiveData<ModelMovieView> getModelMovieViewLiveData() {
        return Transformations.switchMap(dataSourceFactory.getMovieDataSourceMutableLiveData(),MovieDataSource::getModelMovieViewMutableLiveData);
    }

    public void refresh(){
        dataSourceFactory.getMovieDataSourceMutableLiveData().getValue().invalidate();
    }

    public LiveData<ModelMovieView> getRefreshState(){
        return Transformations.switchMap(dataSourceFactory.getMovieDataSourceMutableLiveData(),MovieDataSource::getModelMovieViewMutableLiveData);
    }

    public LiveData<ModelMovieView>getModeMovieNextPage(){
        return Transformations.switchMap(dataSourceFactory.getMovieDataSourceMutableLiveData(),MovieDataSource::getModelMovieViewMutableLiveDataNextPage);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

    public LiveData<ArrayList<ModelGenre>>getModelGenreMovieLiveData(){
        return genreRemoteDataSource.getModelGenresMovieMutableLiveData();
    }

    public LiveData<ArrayList<ModelGenre>>getModelGenreTvLiveData(){
        return genreRemoteDataSource.getModelGenresTvMutableLiveData();
    }
}
