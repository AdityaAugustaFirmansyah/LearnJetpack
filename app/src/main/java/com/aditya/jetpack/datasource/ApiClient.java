package com.aditya.jetpack.datasource;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String API_KEY = "cdae801c86f7bf152ee22e13c6c9577a";
    private static final Retrofit builder = new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    public static final ApiInterface apiInterface = builder.create(ApiInterface.class);
}
