package com.example.kursach_4_0.api;


import com.example.kursach_4_0.api.model.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

// http://api.openweathermap.org/data/2.5/find?q=London,uk&APPID=a3055f039a0d4b58412af5b3edc7cfec

public interface MyApi {

    @GET("forecast")
    Call<Data> getData(@Query("q") String location, @Query("APPID") String token, @Query("lang") String lang);
}
