package com.hcom.topratedmovies.api;

import com.hcom.topratedmovies.domain.TopRatedResult;
import com.hcom.topratedmovies.domain.TvShowDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TopRatedApi {

    String API_KEY = "?api_key=266ff5da69b578ee661cc5835c81bb43";

    @GET("tv/top_rated" + API_KEY)
    Call<TopRatedResult> getTopRatedResult();

    @GET("tv/{tv_id}" + API_KEY)
    Call<TvShowDetails> getDetailsById(@Path("tv_id") int tv_id);
}
