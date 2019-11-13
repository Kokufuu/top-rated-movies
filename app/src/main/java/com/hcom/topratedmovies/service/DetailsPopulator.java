package com.hcom.topratedmovies.service;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hcom.topratedmovies.R;
import com.hcom.topratedmovies.domain.Genre;
import com.hcom.topratedmovies.domain.TvShowDetails;

import java.util.List;
import java.util.stream.Collectors;

public class DetailsPopulator {

    private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original";
    private static final String DELIMITER = ", ";

    public static void populateDetails(Context context, TvShowDetails details) {
        Activity activity = (Activity) context;

        String posterPath = details.getPosterPath();
        String name = details.getName();
        String overview = details.getOverview();
        List<Genre> genres = details.getGenres();
        String originalLanguage = details.getOriginalLanguage();
        float popularity = details.getPopularity();
        int voteCount = details.getVoteCount();

        ImageView imageView = activity.findViewById(R.id.details_image);
        Glide.with(context)
                .asBitmap()
                .load(IMAGE_BASE_URL + posterPath)
                .into(imageView);
        TextView viewName = activity.findViewById(R.id.details_name);
        viewName.setText(name);
        TextView viewOverview = activity.findViewById(R.id.details_overview);
        viewOverview.setText(overview);
        TextView viewGenres = activity.findViewById(R.id.details_genres);
        viewGenres.setText(genres.stream().map(Genre::getName).collect(Collectors.joining(DELIMITER)));
        TextView viewLanguage = activity.findViewById(R.id.details_original_language);
        viewLanguage.setText(originalLanguage);
        TextView viewPopularity = activity.findViewById(R.id.details_popularity);
        viewPopularity.setText(String.valueOf(popularity));
        TextView viewVoteCount = activity.findViewById(R.id.details_vote_count);
        viewVoteCount.setText(String.valueOf(voteCount));
    }
}
