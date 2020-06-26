package com.aditya.jetpack.model;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;

public class ModelMovieView implements Cloneable{
    boolean statusLoading;
    String msgError;
    PagedList<ModelFilm.Result> modelFilms;

    public ModelMovieView(boolean statusLoading, String msgError, PagedList<ModelFilm.Result> modelFilms) {
        this.statusLoading = statusLoading;
        this.msgError = msgError;
        this.modelFilms = modelFilms;
    }

    public boolean isStatusLoading() {
        return statusLoading;
    }

    public void setStatusLoading(boolean statusLoading) {
        this.statusLoading = statusLoading;
    }

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }

    public PagedList<ModelFilm.Result> getModelFilms() {
        return modelFilms;
    }

    public void setModelFilms(PagedList<ModelFilm.Result> modelFilms) {
        this.modelFilms = modelFilms;
    }

    @NonNull
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
