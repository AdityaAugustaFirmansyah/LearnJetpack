package com.aditya.jetpack.model;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;

import com.aditya.jetpack.datasource.ModelTv;

public class ModelTvView implements Cloneable{
    private boolean loading;
    private String msgError;
    private PagedList<ModelTv.Result>results;

    public ModelTvView(boolean loading, String msgError, PagedList<ModelTv.Result> results) {
        this.loading = loading;
        this.msgError = msgError;
        this.results = results;
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }

    public PagedList<ModelTv.Result> getResults() {
        return results;
    }

    public void setResults(PagedList<ModelTv.Result> results) {
        this.results = results;
    }

    @NonNull
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
