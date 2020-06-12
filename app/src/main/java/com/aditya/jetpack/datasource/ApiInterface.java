package com.aditya.jetpack.datasource;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiInterface {
    @GET("movie/popular")
    Call<ModelFilm>getModelFilms(@QueryMap Map<String,String>mapFilm);

    @GET("tv/popular")
    Call<ModelTv>getModelTvs(@QueryMap Map<String,String>mapTv);


    @GET("movie/popular")
    Call<ModelFilm>getModelFilms(@Query("api_key")String api_key,@Query("page")long page);

    @GET("tv/popular")
    Call<ModelTv>getModelTv(@Query("api_key")String api_key,@Query("page")long page);
}
