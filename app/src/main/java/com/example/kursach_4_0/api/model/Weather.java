package com.example.kursach_4_0.api.model;

import com.google.gson.annotations.SerializedName;

public class Weather {
    @SerializedName("id")
    int id;

    @SerializedName("description")
    String description;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

}
