package com.example.kursach_4_0.api.model;

import com.google.gson.annotations.SerializedName;

public class CityCountry {

    @SerializedName("name")
    String name;

    @SerializedName("country")
    String country;


    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }
}
