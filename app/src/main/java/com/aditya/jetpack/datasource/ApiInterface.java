package com.aditya.jetpack.datasource;

import com.aditya.jetpack.model.ModelDetailMovie;
import com.aditya.jetpack.model.ModelDetailTv;
import com.aditya.jetpack.model.ModelGenre;
import com.aditya.jetpack.model.ModelResponseVideo;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("movie/popular")
    Single<ModelFilm> getModelFilms(@Query("api_key")String api_key, @Query("page")long page);

    @GET("tv/popular")
    Single<ModelTv>getModelTv(@Query("api_key")String api_key,@Query("page")long page);

    @GET("genre/movie/list")
    Call<ModelGenre.ModelGenreResponse> getGenreMovie(@Query("api_key")String api_key);

    @GET("genre/tv/list")
    Call<ModelGenre.ModelGenreResponse>getGenreTv(@Query("api_key")String api_key);

    @GET("movie/{movie_id}")
    Single<Response<ModelDetailMovie>>getDetailsMovie(@Path("movie_id") int movie_id, @Query("api_key")String api_key);

    @GET("movie/{movie_id}/videos")
    Single<Response<ModelResponseVideo>>getVideoTrailer(@Path("movie_id") int movie_id, @Query("api_key")String api_key);

    @GET("tv/{tv_id}")
    Single<Response<ModelDetailTv>>getDetailsTv(@Path("tv_id") int tv_id, @Query("api_key")String api_key);

    @GET("tv/{tv_id}/videos")
    Single<Response<ModelResponseVideo>>getVideoTrailerTv(@Path("tv_id") int movie_id, @Query("api_key")String api_key);
}