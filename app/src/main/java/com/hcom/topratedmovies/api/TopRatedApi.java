package com.hcom.topratedmovies.api;

import com.hcom.topratedmovies.domain.TopRatedResult;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TopRatedApi {

    @GET("tv/top_rated")
    Call<TopRatedResult> getTopRatedresult();
}
