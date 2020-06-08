package com.example.kursach_4_0.api;

import com.example.kursach_4_0.api.model.DataCity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MySecondApi {
    @GET("forecast")
    Call<DataCity> getData(@Query("q") String location, @Query("APPID") String token, @Query("lang") String lang);
}
