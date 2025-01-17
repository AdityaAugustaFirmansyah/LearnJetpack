package com.aditya.jetpack.model;

import androidx.annotation.NonNull;

public class ModelTvView implements Cloneable{
    private boolean loading;
    private String msgError;

    public ModelTvView(boolean loading, String msgError) {
        this.loading = loading;
        this.msgError = msgError;
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


    @NonNull
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
