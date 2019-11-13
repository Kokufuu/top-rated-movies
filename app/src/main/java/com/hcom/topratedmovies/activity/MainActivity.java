package com.hcom.topratedmovies.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hcom.topratedmovies.R;
import com.hcom.topratedmovies.adapter.RecyclerViewAdapter;
import com.hcom.topratedmovies.api.TopRatedApi;
import com.hcom.topratedmovies.domain.TvShow;
import com.hcom.topratedmovies.domain.TopRatedResult;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    private List<TvShow> tvShows = Collections.emptyList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, tvShows);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.super.getApplicationContext()));

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        TopRatedApi topRatedApi = retrofit.create(TopRatedApi.class);

        Call<TopRatedResult> call = topRatedApi.getTopRatedResult();

        call.enqueue(new Callback<TopRatedResult>() {
            @Override
            public void onResponse(Call<TopRatedResult> call, Response<TopRatedResult> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "onResponse was unsuccessful");
                    return;
                }
                Log.d(TAG, "onResponse was successful");
                TopRatedResult topRatedResult = response.body();
                List<TvShow> tvShows = topRatedResult.getResults();
                adapter.setTvShows(tvShows);
            }

            @Override
            public void onFailure(Call<TopRatedResult> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });
    }
}
