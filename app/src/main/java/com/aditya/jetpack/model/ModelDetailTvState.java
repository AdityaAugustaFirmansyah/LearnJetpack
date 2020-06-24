package com.aditya.jetpack.model;

public class ModelDetailTvState {
    private boolean loading;
    private String msgError;
    private ModelDetailTv modelDetailTv;

    public ModelDetailTvState(boolean loading, String msgError, ModelDetailTv modelDetailTv) {
        this.loading = loading;
        this.msgError = msgError;
        this.modelDetailTv = modelDetailTv;
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

    public ModelDetailTv getModelDetailTv() {
        return modelDetailTv;
    }

    public void setModelDetailTv(ModelDetailTv modelDetailTv) {
        this.modelDetailTv = modelDetailTv;
    }
}
