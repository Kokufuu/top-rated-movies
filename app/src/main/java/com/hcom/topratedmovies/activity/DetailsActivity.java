package com.hcom.topratedmovies.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hcom.topratedmovies.R;
import com.hcom.topratedmovies.api.TopRatedApi;
import com.hcom.topratedmovies.domain.Genre;
import com.hcom.topratedmovies.domain.TvShowDetails;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = "DetailsActivity";
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        int tvShowId = getIdFromIncomingIntent();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        TopRatedApi topRatedApi = retrofit.create(TopRatedApi.class);

        Call<TvShowDetails> call = topRatedApi.getDetailsById(tvShowId);

        call.enqueue(new Callback<TvShowDetails>() {
            @Override
            public void onResponse(Call<TvShowDetails> call, Response<TvShowDetails> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "onResponse was unsuccessful");
                    return;
                }
                Log.d(TAG, "onResponse was successful");
                TvShowDetails details = response.body();
                populateDetailsLayout(details);
            }

            @Override
            public void onFailure(Call<TvShowDetails> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });
    }

    private int getIdFromIncomingIntent() {
        int id = 0;
        if(getIntent().hasExtra("tvShow")) {
            id = getIntent().getIntExtra("tvShow", 0);
        }
        return id;
    }

    private void populateDetailsLayout(TvShowDetails details) {
        String posterPath = details.getPosterPath();
        String name = details.getName();
        String overview = details.getOverview();
        List<Genre> genres = details.getGenres();
        String originalLanguage = details.getOriginalLanguage();
        float popularity = details.getPopularity();
        int voteCount = details.getVoteCount();

        ImageView imageView = findViewById(R.id.details_image);
        Glide.with(this)
                .asBitmap()
                .load(IMAGE_BASE_URL + posterPath)
                .into(imageView);
        TextView viewName = findViewById(R.id.details_name);
        viewName.setText(name);
        TextView viewOverview = findViewById(R.id.details_overview);
        viewOverview.setText(overview);
        TextView viewGenres = findViewById(R.id.details_genres);
        viewGenres.setText(createGenreList(genres));
        TextView viewLanguage = findViewById(R.id.details_original_language);
        viewLanguage.setText(originalLanguage);
        TextView viewPopularity = findViewById(R.id.details_popularity);
        viewPopularity.setText(String.valueOf(popularity));
        TextView viewVoteCount = findViewById(R.id.details_vote_count);
        viewVoteCount.setText(""+voteCount);
    }

    private String createGenreList(List<Genre> genres) {
        StringBuilder genreList = new StringBuilder();
        for (Genre genre : genres) {
            if (genreList.length() == 0) {
                genreList.append(genre.getName());
            } else {
                genreList.append(", ");
                genreList.append(genre.getName());
            }
        }
        return genreList.toString();
    }
}
