package com.hcom.topratedmovies.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopRatedResult {

    private int page;
    private List<TvShow> results;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;

    public int getPage() {
        return page;
    }

    public List<TvShow> getResults() {
        return results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
