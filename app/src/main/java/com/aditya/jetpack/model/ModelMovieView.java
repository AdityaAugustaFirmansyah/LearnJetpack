package com.aditya.jetpack.model;

import com.aditya.jetpack.datasource.ModelFilm;

import java.util.ArrayList;

public class ModelMovieView {
    boolean statusLoading;
    int statusCode;
    String msgError;
    ArrayList<ModelFilm.Result> modelFilms;

    public boolean isStatusLoading() {
        return statusLoading;
    }

    public void setStatusLoading(boolean statusLoading) {
        this.statusLoading = statusLoading;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }

    public ArrayList<ModelFilm.Result> getModelFilms() {
        return modelFilms;
    }

    public void setModelFilms(ArrayList<ModelFilm.Result> modelFilms) {
        this.modelFilms = modelFilms;
    }
}
