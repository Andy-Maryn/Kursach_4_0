package com.example.kursach_4_0.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("list")
    private List<Day> dayList;

    public List<Day> getDayList() {
        return dayList;
    }
}
