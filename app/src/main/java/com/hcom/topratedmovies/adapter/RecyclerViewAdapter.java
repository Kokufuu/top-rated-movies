package com.hcom.topratedmovies.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hcom.topratedmovies.R;
import com.hcom.topratedmovies.activity.DetailsActivity;
import com.hcom.topratedmovies.domain.TvShow;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String INTENT_NAME = "tvShow";

    private Context context;
    private List<TvShow> tvShows;

    public RecyclerViewAdapter(Context context, List<TvShow> tvShows) {
        this.context = context;
        this.tvShows = tvShows;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TvShow actual = tvShows.get(position);
        holder.movieId = actual.getId();
        holder.name.setText(actual.getName());
        String releaseText = context.getResources().getString(R.string.release_year);
        releaseText = String.format(releaseText, extractYear(actual));
        holder.year.setText(releaseText);
        String voteCountText = context.getResources().getString(R.string.vote_count);
        voteCountText = String.format(voteCountText, actual.getVoteCount());
        holder.voteCount.setText(voteCountText);
        String voteAvgText = context.getResources().getString(R.string.vote_average);
        voteAvgText = String.format(voteAvgText, actual.getVoteAverage());
        holder.voteAverage.setText(voteAvgText);

        holder.parentLayout.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra(INTENT_NAME, tvShows.get(position).getId());
            context.startActivity(intent);
        });
    }

    private String extractYear(TvShow actual) {
        return actual.getFirstAirDate().substring(0, 4);
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        int movieId;
        LinearLayout parentLayout;
        TextView name;
        TextView year;
        TextView voteCount;
        TextView voteAverage;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            name = itemView.findViewById(R.id.movie_item_name);
            year = itemView.findViewById(R.id.movie_item_year);
            voteCount = itemView.findViewById(R.id.movie_item_vote_count);
            voteAverage = itemView.findViewById(R.id.movie_item_vote_average);
        }
    }

    public void setTvShows(List<TvShow> tvShows) {
        this.tvShows = tvShows;
        notifyDataSetChanged();
    }
}
