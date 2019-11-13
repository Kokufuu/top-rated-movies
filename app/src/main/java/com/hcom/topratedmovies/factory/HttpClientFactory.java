package com.hcom.topratedmovies.factory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hcom.topratedmovies.api.TopRatedApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpClientFactory {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    public static TopRatedApi createClient() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(TopRatedApi.class);
    }
}
