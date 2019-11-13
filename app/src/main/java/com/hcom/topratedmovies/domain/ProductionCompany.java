package com.hcom.topratedmovies.domain;

import com.google.gson.annotations.SerializedName;

public class ProductionCompany {

    private int id;
    @SerializedName("logo_path")
    private String logoPath;
    private String name;
    @SerializedName("origin_country")
    private String originCountry;

    public int getId() {
        return id;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public String getName() {
        return name;
    }

    public String getOriginCountry() {
        return originCountry;
    }
}
