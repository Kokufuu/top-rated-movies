package com.hcom.topratedmovies.domain;

import com.google.gson.annotations.SerializedName;

public class Network {

    private String name;
    private int id;
    @SerializedName("logo_path")
    private String logoPath;
    @SerializedName("origin_country")
    private String originCountry;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public String getOriginCountry() {
        return originCountry;
    }
}
