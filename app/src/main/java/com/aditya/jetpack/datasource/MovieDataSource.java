package com.aditya.jetpack.datasource;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.aditya.jetpack.model.ModelMovieView;

import io.reactivex.Completable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;

@SuppressWarnings("unchecked")
public class MovieDataSource extends PageKeyedDataSource<Long, ModelFilm.Result> {
    private ApiInterface apiInterface;
    private String apiKey = "cdae801c86f7bf152ee22e13c6c9577a";
    private CompositeDisposable compositeDisposable;
    private MutableLiveData<ModelMovieView>modelMovieViewMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ModelMovieView>modelMovieViewMutableLiveDataNextPage = new MutableLiveData<>();
    private Completable completableRetry;

    public MutableLiveData<ModelMovieView> getModelMovieViewMutableLiveData() {
        return modelMovieViewMutableLiveData;
    }

    public MovieDataSource(ApiInterface apiInterface, CompositeDisposable compositeDisposable) {
        this.apiInterface = apiInterface;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull final LoadInitialCallback callback) {
        modelMovieViewMutableLiveData.postValue(new ModelMovieView(true,null,null));
        modelMovieViewMutableLiveDataNextPage.postValue(new ModelMovieView(true,null,null));
        compositeDisposable.add(apiInterface.getModelFilms(apiKey,1).subscribe(modelFilm -> {
            setCompletableRetry(null);
            modelMovieViewMutableLiveData.postValue(new ModelMovieView(false,null,null));
            modelMovieViewMutableLiveDataNextPage.postValue(new ModelMovieView(false,null,null));
            callback.onResult(modelFilm.results,null,(long)2);
        }, throwable->{
            modelMovieViewMutableLiveDataNextPage.postValue(new ModelMovieView(false,throwable.getLocalizedMessage(),null));
            modelMovieViewMutableLiveData.postValue(new ModelMovieView(false,throwable.getLocalizedMessage(),null));
        }));
    }

    @Override
    public void loadBefore(@NonNull LoadParams params, @NonNull LoadCallback callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, ModelFilm.Result> callback) {
            modelMovieViewMutableLiveDataNextPage.postValue(new ModelMovieView(true,null,null));
            compositeDisposable.add(apiInterface.getModelFilms(apiKey,params.key).subscribe(modelFilm -> {
                setCompletableRetry(null);
                modelMovieViewMutableLiveDataNextPage.postValue(new ModelMovieView(false,null,null));
                callback.onResult(modelFilm.results, params.key +1);
            }, throwable -> modelMovieViewMutableLiveDataNextPage.postValue(new ModelMovieView(true,throwable.getLocalizedMessage(),null))));

    }

    public MutableLiveData<ModelMovieView> getModelMovieViewMutableLiveDataNextPage() {
        return modelMovieViewMutableLiveDataNextPage;
    }

    private void setCompletableRetry(Action action){
        if (action == null){
            this.completableRetry = null;
        }else {
            this.completableRetry = Completable.fromAction(action);
        }
    }
}