package com.hcom.topratedmovies.api;

import com.hcom.topratedmovies.domain.TopRatedResult;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TopRatedApi {

    String API_KEY = "?api_key=266ff5da69b578ee661cc5835c81bb43";

    @GET("tv/top_rated" + API_KEY)
    Call<TopRatedResult> getTopRatedresult();
}
