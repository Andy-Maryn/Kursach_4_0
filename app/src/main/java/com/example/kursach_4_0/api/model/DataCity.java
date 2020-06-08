package com.example.kursach_4_0.api.model;

import com.google.gson.annotations.SerializedName;

public class DataCity {
    @SerializedName("city")
    private CityCountry cityCountries;

    public CityCountry getCityCountries() {
        return cityCountries;
    }
}
