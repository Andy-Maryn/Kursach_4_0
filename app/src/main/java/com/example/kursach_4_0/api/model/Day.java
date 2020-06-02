package com.example.kursach_4_0.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Day {


    @SerializedName("dt")
    long dateLong;

    @SerializedName("main")
    MainData mainData;

    @SerializedName("weather")
    List<Weather> weather;


    //@SerializedName("weather")
    //Town id;

    @SerializedName("wind")
    Wind wind;



    public Date getDate() {
        return new Date(dateLong * 1000);
    }

    public MainData getMainData() {
        return mainData;
    }

    public Weather getWeather() {
        return weather.get(0);
    }

    public Wind getWind() {
        return wind;
    }
}


