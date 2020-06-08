package com.example.kursach_4_0.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataCity {
    @SerializedName("city")
    private List<CityCountry> cityCountries;

    public List<CityCountry> getCityCountries() {
        return cityCountries;
    }
}
