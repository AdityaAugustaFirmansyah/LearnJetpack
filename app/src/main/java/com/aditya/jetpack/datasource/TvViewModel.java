package com.aditya.jetpack.datasource;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.concurrent.Executors;

public class TvViewModel extends AndroidViewModel {
    private LiveData<PagedList<ModelTv.Result>>listLiveData;

    public TvViewModel(@NonNull Application application) {
        super(application);

        TvDataSourceFactory tvDataSourceFactory = new TvDataSourceFactory(application);
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();

        listLiveData = new LivePagedListBuilder<Long,ModelTv.Result>(tvDataSourceFactory,config)
                .setFetchExecutor(Executors.newFixedThreadPool(5)).build();
    }

    public LiveData<PagedList<ModelTv.Result>> getListLiveData() {
        return listLiveData;
    }
}
