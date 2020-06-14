package com.aditya.jetpack.datasource;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String API_KEY = "6f7e6b4fd171ee5a84c759606b18dfa4";
    private static final Retrofit builder = new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static final ApiInterface apiInterface = builder.create(ApiInterface.class);
}
