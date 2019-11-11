package com.hcom.topratedmovies;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hcom.topratedmovies.adapter.RecyclerViewAdapter;
import com.hcom.topratedmovies.api.TopRatedApi;
import com.hcom.topratedmovies.domain.Movie;
import com.hcom.topratedmovies.domain.TopRatedResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://developers.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final TopRatedApi topRatedApi = retrofit.create(TopRatedApi.class);

        Call<TopRatedResult> call = topRatedApi.getTopRatedresult();

        call.enqueue(new Callback<TopRatedResult>() {
            @Override
            public void onResponse(Call<TopRatedResult> call, Response<TopRatedResult> response) {
                if (!response.isSuccessful()) {
                    // Set some error message
                    return;
                }
                TopRatedResult topRatedResult = response.body();
                List<Movie> movies = topRatedResult.getResults();
                RecyclerViewAdapter adapter = new RecyclerViewAdapter(movies);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.super.getApplicationContext()));
            }

            @Override
            public void onFailure(Call<TopRatedResult> call, Throwable t) {
                // Set some error message
            }
        });
    }
}
