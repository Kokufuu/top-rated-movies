package com.hcom.topratedmovies.domain;

import com.google.gson.annotations.SerializedName;

public class Season {

    @SerializedName("air_date")
    private String airDate;
    @SerializedName("episode_count")
    private int episodeCount;
    private int id;
    private String name;
    private String overview;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("season_number")
    private int seasonNumber;

    public String getAirDate() {
        return airDate;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }
}
