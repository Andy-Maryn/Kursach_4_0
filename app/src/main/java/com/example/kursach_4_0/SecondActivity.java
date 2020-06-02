package com.example.kursach_4_0;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kursach_4_0.adapter.TownAdapter;
import com.example.kursach_4_0.adapter.WeatherAdapter;
import com.example.kursach_4_0.api.MyService;
import com.example.kursach_4_0.api.model.Data;
import com.example.kursach_4_0.api.model.Day;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity implements TownAdapter.ItemClickListener, WeatherAdapter.ItemClickListener {

    ArrayList<Float> windSpeed = new ArrayList<>();
    ArrayList<Float> windDegree = new ArrayList<>();
    ArrayList<String> weatherDescription = new ArrayList<>();
    ArrayList<Float> mainDataTemperature = new ArrayList<>();
    ArrayList<Date> dataDayList = new ArrayList<>();

    WeatherAdapter weatherAdapter;
    TownAdapter townAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle arguments = getIntent().getExtras();
        String name = arguments.get("return").toString();

        weatherAdapter = new WeatherAdapter(this, weatherDescription);
        townAdapter = new TownAdapter(this, null);

        // data to populate the RecyclerView with
        ArrayList<String> townList = new ArrayList<>();

        MyService.createRetrofit().getData(name, MyService.KEY, "ru").enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                for (Day day : response.body().getDayList()) {
                    dataDayList.add(day.getDate());
                    mainDataTemperature.add(day.getMainData().getTemperature());
                    windDegree.add(day.getWind().getDegree());
                    windSpeed.add(day.getWind().getSpeed());
                    weatherDescription.add(day.getWeather().getDescription());

                    weatherAdapter.setData(weatherDescription);
                    weatherAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                System.out.println("error");
                System.out.println(t.getMessage());
            }
        });

        System.out.println(weatherDescription);

        townList.add(name);
        townList.add("qwe");
        townList.add("qwe");
        townList.add("wer");
        townList.add("ert");
        townList.add("rty");
        //townList.add("Odessa");

        // set up the RecyclerView vertical
        RecyclerView recyclerView = findViewById(R.id.recyclerTowns);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        townAdapter.setClickListener(this);
        recyclerView.setAdapter(townAdapter);

        // set up the RecyclerView horizontal
        RecyclerView recyclerViewSecond = findViewById(R.id.recyclerWeather);
        recyclerViewSecond.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        weatherAdapter.setClickListener(this);

        recyclerViewSecond.setAdapter(weatherAdapter);


        //weatherAdapter.notifyItemRemoved();
        // townList.clear();
        // weatherAdapter.notifyDataSetChanged();
        // recyclerViewSecond.setAdapter(weatherAdapter);

    }


    @Override
    public void onItemClick(View view, int position) {

    }
}