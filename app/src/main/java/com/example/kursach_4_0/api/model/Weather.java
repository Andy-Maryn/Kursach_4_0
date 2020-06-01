package com.example.kursach_4_0.api.model;

import com.google.gson.annotations.SerializedName;

public class Weather {
    @SerializedName("description")
    String description;

    public String getDescription() {
        return description;
    }
}
