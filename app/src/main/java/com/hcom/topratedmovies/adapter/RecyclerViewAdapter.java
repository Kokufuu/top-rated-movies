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
        String simpleConcat = actual.getName() + "\t\t" + actual.getFirst_air_date().substring(0,4)
                + "\nVote count: " + actual.getVote_count() + "\t\tVote avg: " + actual.getVote_average();
        holder.textView.setText(simpleConcat);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout parentLayout;
        TextView textView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            textView = itemView.findViewById(R.id.movie_item);
        }
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }
}
