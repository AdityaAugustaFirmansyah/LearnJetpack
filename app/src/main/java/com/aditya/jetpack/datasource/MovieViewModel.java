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

import com.aditya.jetpack.model.ModelMovieView;

import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("unchecked")
public class MovieViewModel extends AndroidViewModel {
    private DataSourceFactory dataSourceFactory;
    private MutableLiveData<ModelMovieView> modelMovieViewMutableLiveData = new MutableLiveData<>();
    private PagedList.Config config;
    private ModelMovieView modelMovieView = new ModelMovieView(true, null, null);

    public MovieViewModel(@NonNull Application application) {
        super(application);
        getMovies();
    }

    public void getMovies() {
        AsyncTask.execute(new Runnable() {
            @SuppressWarnings("rawtypes")
            @Override
            public void run() {
                try {
                    ApiInterface apiInterface = ApiClient.apiInterface;
                    dataSourceFactory = new DataSourceFactory(apiInterface);
                    config = (new PagedList.Config.Builder())
                            .setEnablePlaceholders(true)
                            .setInitialLoadSizeHint(10)
                            .setPageSize(20)
                            .setPrefetchDistance(4)
                            .build();

                    PagedList listLiveDataPage = (new PagedList.Builder(dataSourceFactory.create(), config)).setNotifyExecutor(new Executor() {
                        private Handler handler = new Handler(Looper.getMainLooper());

                        @Override
                        public void execute(Runnable runnable) {
                            handler.post(runnable);
                        }
                    }).setFetchExecutor(new Executor() {
                        private ExecutorService executorService = Executors.newFixedThreadPool(2);

                        @Override
                        public void execute(Runnable runnable) {
                            executorService.execute(runnable);
                        }
                    }).build();
                    modelMovieView = (ModelMovieView) Objects.requireNonNull(modelMovieViewMutableLiveData.getValue()).clone();
                    modelMovieView.setStatusLoading(false);
                    modelMovieView.setMsgError(null);
                    modelMovieView.setModelFilms(listLiveDataPage);
                    modelMovieViewMutableLiveData.postValue(modelMovieView);
                } catch (Exception e) {
                    try {
                        modelMovieView = (ModelMovieView) Objects.requireNonNull(modelMovieViewMutableLiveData.getValue()).clone();
                    } catch (CloneNotSupportedException ex) {
                        ex.printStackTrace();
                    }
                    modelMovieView.setStatusLoading(false);
                    modelMovieView.setMsgError(e.getLocalizedMessage());
                    modelMovieView.setModelFilms(null);
                    modelMovieViewMutableLiveData.postValue(modelMovieView);
                }
            }
        });
    }

    public LiveData<ModelMovieView> getMovieViewModelLiveData() {
        modelMovieViewMutableLiveData.setValue(modelMovieView);
        return modelMovieViewMutableLiveData;
    }
}
