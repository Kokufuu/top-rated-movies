package com.hcom.topratedmovies.domain;

import com.google.gson.annotations.SerializedName;

public class LastEpisodeToAir {

    @SerializedName("air_date")
    private String airDate;
    @SerializedName("episode_number")
    private int episodeNumber;
    private int id;
    private String name;
    private String overview;
    @SerializedName("production_code")
    private String productionCode;
    @SerializedName("season_number")
    private int seasonNumber;
    @SerializedName("show_id")
    private int showId;
    @SerializedName("still_path")
    private String stillPath;
    @SerializedName("vote_average")
    private float voteAverage;
    @SerializedName("vote_count")
    private int voteCount;

    public String getAirDate() {
        return airDate;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
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

    public String getProductionCode() {
        return productionCode;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public int getShowId() {
        return showId;
    }

    public String getStillPath() {
        return stillPath;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }
}
