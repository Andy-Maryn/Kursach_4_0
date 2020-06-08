package com.example.kursach_4_0.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyService {
    public static String KEY = "a3055f039a0d4b58412af5b3edc7cfec";

    public static MyApi createRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(MyApi.class);
    }
    public static MySecondApi createRetrofitCity() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(MySecondApi.class);
    }
}
