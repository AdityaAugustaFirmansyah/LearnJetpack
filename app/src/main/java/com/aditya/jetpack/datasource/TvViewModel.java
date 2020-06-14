package com.aditya.jetpack.datasource;

import android.app.Application;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;

import com.aditya.jetpack.model.ModelTvView;

import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@SuppressWarnings("unchecked")
public class TvViewModel extends AndroidViewModel {

    @SuppressWarnings("rawtypes")
    private PagedList listLiveData;
    private MutableLiveData<ModelTvView>modelTvViewMutableLiveData = new MutableLiveData<>();
    private ModelTvView modelTvView = new ModelTvView(true,null,null);

    public TvViewModel(@NonNull Application application) {
        super(application);
        getTvs();
    }

    public LiveData<ModelTvView> getModelTvViewLiveData() {
        modelTvViewMutableLiveData.setValue(modelTvView);
        return modelTvViewMutableLiveData;
    }

    public void getTvs(){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try{
                    TvDataSourceFactory tvDataSourceFactory = new TvDataSourceFactory();
                    PagedList.Config config = new PagedList.Config.Builder()
                            .setEnablePlaceholders(true)
                            .setInitialLoadSizeHint(10)
                            .setPageSize(20)
                            .setPrefetchDistance(4)
                            .build();

                    listLiveData = new PagedList.Builder<Long,ModelTv.Result>(tvDataSourceFactory.create(),config)
                            .setNotifyExecutor(new Executor() {
                                private Handler handler = new Handler(Looper.getMainLooper());
                                @Override
                                public void execute(Runnable runnable) {
                                    handler.post(runnable);
                                }
                            }).setFetchExecutor(Executors.newFixedThreadPool(5)).build();
                    modelTvView = (ModelTvView) Objects.requireNonNull(modelTvViewMutableLiveData.getValue()).clone();
                    modelTvView.setLoading(false);
                    modelTvView.setMsgError(null);
                    modelTvView.setResults(listLiveData);
                    modelTvViewMutableLiveData.postValue(modelTvView);
                }catch (Exception e){
                    try {
                        modelTvView = (ModelTvView) Objects.requireNonNull(modelTvViewMutableLiveData.getValue()).clone();
                    } catch (CloneNotSupportedException ex) {
                        ex.printStackTrace();
                    }
                    modelTvView.setLoading(false);
                    modelTvView.setResults(null);
                    modelTvView.setMsgError(e.getLocalizedMessage());
                    modelTvViewMutableLiveData.postValue(modelTvView);
                }
            }
        });
    }

}
