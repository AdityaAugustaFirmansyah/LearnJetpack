package com.aditya.jetpack.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.aditya.jetpack.api.ApiClient;
import com.aditya.jetpack.api.ApiInterface;
import com.aditya.jetpack.datasource.GenreRemoteDataSource;
import com.aditya.jetpack.model.ModelTv;
import com.aditya.jetpack.datasource.TvDataSource;
import com.aditya.jetpack.datasource.TvDataSourceFactory;
import com.aditya.jetpack.model.ModelGenre;
import com.aditya.jetpack.model.ModelTvView;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;

@SuppressWarnings("unchecked")
public class TvViewModel extends AndroidViewModel {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private TvDataSourceFactory tvDataSourceFactory;
    private LiveData<PagedList<ModelTv.Result>> listLiveData;
    private GenreRemoteDataSource genreRemoteDataSource;

    public TvViewModel(@NonNull Application application) {
        super(application);
        ApiInterface apiInterface = ApiClient.apiInterface;
        genreRemoteDataSource = new GenreRemoteDataSource(apiInterface);
        genreRemoteDataSource.getGenreTv();
        tvDataSourceFactory = new TvDataSourceFactory(compositeDisposable,apiInterface);
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(2)
                .setPageSize(1)
                .build();

        listLiveData = new LivePagedListBuilder(tvDataSourceFactory,config).build();
    }

    public LiveData<PagedList<ModelTv.Result>> getListLiveData() {
        return listLiveData;
    }

    public LiveData<ModelTvView> getModelTvViewLiveData() {
        return Transformations.switchMap(tvDataSourceFactory.getMovieDataSourceMutableLiveData(), TvDataSource::getLoadInitial);
    }

    public LiveData<ModelTvView> getNetworkStateLiveData() {
        return Transformations.switchMap(tvDataSourceFactory.getMovieDataSourceMutableLiveData(),TvDataSource::getNetworkState);
    }

    public LiveData<ArrayList<ModelGenre>>getModelGenreLiveData(){
        return genreRemoteDataSource.getModelGenresTvMutableLiveData();
    }

    public void refresh(){
        tvDataSourceFactory.getMovieDataSourceMutableLiveData().getValue().invalidate();
    }
}
