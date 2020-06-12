package com.aditya.jetpack.datasource;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.aditya.jetpack.model.ModelMovieView;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MovieViewModel extends AndroidViewModel {
    private MovieRepository movieRepository;
    private Executor executor;
    private LiveData<PagedList<ModelFilm.Result>>listLiveDataPage;
    private MutableLiveData<ModelMovieView>movieViewModelMutableLiveData;
    public MovieViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository();
        ApiInterface apiInterface = ApiClient.apiInterface;
        DataSourceFactory dataSourceFactory = new DataSourceFactory(apiInterface,application);

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();

        executor = Executors.newFixedThreadPool(5);
        listLiveDataPage = (new LivePagedListBuilder<Long,ModelFilm.Result>(dataSourceFactory,config))
                .setFetchExecutor(executor)
                .build();

        movieViewModelMutableLiveData = dataSourceFactory.getModelMovieViewMutableLiveData();
        Log.d("TAG_DATA_SOURCE", "create: "+(dataSourceFactory.getModelMovieViewMutableLiveData()!=null));
    }

    public LiveData<PagedList<ModelFilm.Result>> getListLiveDataPage() {
        return listLiveDataPage;
    }

    public MutableLiveData<ModelMovieView> getMovieViewModelMutableLiveData() {
        return movieViewModelMutableLiveData;
    }
}
