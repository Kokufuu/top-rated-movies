package com.hcom.topratedmovies.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hcom.topratedmovies.R;
import com.hcom.topratedmovies.adapter.RecyclerViewAdapter;
import com.hcom.topratedmovies.api.TopRatedApi;
import com.hcom.topratedmovies.domain.TopRatedResult;
import com.hcom.topratedmovies.domain.TvShow;
import com.hcom.topratedmovies.factory.HttpClientFactory;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String ON_RESPONSE_UNSUCCESSFUL = "onResponse was unsuccessful";
    private static final String ON_FAILURE = "onFailure: ";

    private TopRatedApi topRatedApi;
    private List<TvShow> tvShows;

    public MainActivity() {
        this.topRatedApi = HttpClientFactory.createClient();
        this.tvShows = Collections.emptyList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, tvShows);
        recyclerView.setAdapter(adapter);

        Call<TopRatedResult> call = topRatedApi.getTopRatedResult();

        call.enqueue(new Callback<TopRatedResult>() {
            @Override
            public void onResponse(@NonNull Call<TopRatedResult> call, @NonNull Response<TopRatedResult> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setTvShows(response.body().getResults());
                } else {
                    Log.d(TAG, ON_RESPONSE_UNSUCCESSFUL);
                }
            }

            @Override
            public void onFailure(@NonNull Call<TopRatedResult> call, @NonNull Throwable t) {
                Log.d(TAG, ON_FAILURE + t.toString());
            }
        });
    }
}
