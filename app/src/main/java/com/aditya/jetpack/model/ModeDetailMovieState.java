package com.aditya.jetpack.model;

public class ModeDetailMovieState {
    private boolean loading;
    private String msgError;
    private ModelDetailMovie modelDetailMovie;

    public ModeDetailMovieState(boolean loading, String msgError, ModelDetailMovie modelDetailMovie) {
        this.loading = loading;
        this.msgError = msgError;
        this.modelDetailMovie = modelDetailMovie;
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

    public ModelDetailMovie getModelDetailMovie() {
        return modelDetailMovie;
    }

    public void setModelDetailMovie(ModelDetailMovie modelDetailMovie) {
        this.modelDetailMovie = modelDetailMovie;
    }
}
