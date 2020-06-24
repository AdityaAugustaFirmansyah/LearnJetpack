package com.aditya.jetpack.model;

public class ModelViewTrailerState {
    private boolean loading;
    private String msgError;
    private ModelResponseVideo.Results modelResponseVideo;

    public ModelViewTrailerState(boolean loading, String msgError, ModelResponseVideo.Results modelResponseVideo) {
        this.loading = loading;
        this.msgError = msgError;
        this.modelResponseVideo = modelResponseVideo;
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

    public ModelResponseVideo.Results getModelResponseVideo() {
        return modelResponseVideo;
    }

    public void setModelResponseVideo(ModelResponseVideo.Results modelResponseVideo) {
        this.modelResponseVideo = modelResponseVideo;
    }
}
