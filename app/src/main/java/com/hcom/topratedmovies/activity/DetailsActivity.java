package com.hcom.topratedmovies.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hcom.topratedmovies.R;
import com.hcom.topratedmovies.api.TopRatedApi;
import com.hcom.topratedmovies.domain.TvShowDetails;
import com.hcom.topratedmovies.factory.HttpClientFactory;
import com.hcom.topratedmovies.service.DetailsPopulator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = "DetailsActivity";
    private static final String ON_RESPONSE_UNSUCCESSFUL = "onResponse was unsuccessful";
    private static final String ON_FAILURE = "onFailure: ";
    private static final String INTENT_NAME = "tvShow";
    private static final int DEFAULT_VALUE = 0;

    private TopRatedApi topRatedApi;

    public DetailsActivity() {
        this.topRatedApi = HttpClientFactory.createClient();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Call<TvShowDetails> call = topRatedApi.getDetailsById(getIdFromIncomingIntent());

        call.enqueue(new Callback<TvShowDetails>() {
            @Override
            public void onResponse(@NonNull Call<TvShowDetails> call, @NonNull Response<TvShowDetails> response) {
                if (response.isSuccessful() && response.body() != null) {
                    DetailsPopulator.populateDetails(DetailsActivity.this, response.body());
                } else {
                    Log.d(TAG, ON_RESPONSE_UNSUCCESSFUL);
                }
            }

            @Override
            public void onFailure(@NonNull Call<TvShowDetails> call, @NonNull Throwable t) {
                Log.d(TAG, ON_FAILURE + t.toString());
            }
        });
    }

    private int getIdFromIncomingIntent() {
        int id = DEFAULT_VALUE;
        if (getIntent().hasExtra(INTENT_NAME)) {
            id = getIntent().getIntExtra(INTENT_NAME, DEFAULT_VALUE);
        }
        return id;
    }
}
