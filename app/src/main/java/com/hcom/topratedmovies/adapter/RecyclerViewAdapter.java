package com.hcom.topratedmovies.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hcom.topratedmovies.R;
import com.hcom.topratedmovies.domain.Movie;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Movie> movies;

    public RecyclerViewAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie actual = movies.get(position);
        holder.name.setText(actual.getName());
        holder.year.setText(actual.getFirst_air_date().substring(0, 4));
        holder.voteCount.setText("Vote count: " + actual.getVote_count());
        holder.voteAverage.setText("Vote average: " + String.valueOf(actual.getVote_average()));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout parentLayout;
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

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }
}
