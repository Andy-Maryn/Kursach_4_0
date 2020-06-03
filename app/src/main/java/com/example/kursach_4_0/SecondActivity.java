package com.example.kursach_4_0;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.OnScrollListener;

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
    int picselItem = 0;
    int picselSee = 0;
    private static final int picselSize = 10920;

    GridLayoutManager layoutManager;


    WeatherAdapter weatherAdapter;
    TownAdapter townAdapter;

    RecyclerView recyclerView;
    RecyclerView recyclerViewSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle arguments = getIntent().getExtras();
        String name = arguments.get("return").toString();

        weatherAdapter = new WeatherAdapter(this,
                weatherDescription,
                mainDataTemperature,
                windDegree,
                windSpeed,
                dataDayList);
        townAdapter = new TownAdapter(this, dataDayList);




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
                    townAdapter.setData(dataDayList);
                    weatherAdapter.notifyDataSetChanged();
                    townAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                System.out.println("error");
                System.out.println(t.getMessage());
            }
        });

        //System.out.println(weatherDescription);

        townList.add(name);
        townList.add("qwe");
        townList.add("qwe");
        townList.add("wer");
        townList.add("ert");
        townList.add("rty");
        //townList.add("Odessa");

        // set up the RecyclerView vertical
        // RecyclerView recyclerView = findViewById(R.id.recyclerTowns);
        recyclerView = findViewById(R.id.recyclerTowns);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        townAdapter.setClickListener(this);

        recyclerView.setAdapter(townAdapter);

        // set up the RecyclerView horizontal
        // RecyclerView recyclerViewSecond = findViewById(R.id.recyclerWeather);
        recyclerViewSecond = findViewById(R.id.recyclerWeather);
        recyclerViewSecond.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        weatherAdapter.setClickListener(this);

        //recyclerViewSecond.scrollToPosition(7);

        recyclerViewSecond.setAdapter(weatherAdapter);

        //GridLayoutManager layoutManager = ((GridLayoutManager)recyclerViewSecond.getLayoutManager());
        //int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();

        //recyclerViewSecond.OnScrollListener();
        final OnScrollListener onScrollListener = new OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                picselItem = picselSize/recyclerViewSecond.getAdapter().getItemCount();
                picselSee += dx;
                int iterator = 102;
                if (picselItem>0){
                    iterator = Math.round(picselSee/picselItem);
                    System.out.println(iterator);
                }

                //System.out.println(picselSee);
                //System.out.println(picselItem);
                //count += dx;
                //System.out.println(count);
                //System.out.println(dx);
                //System.out.println(dy);
                //System.out.println(recyclerView);
                //GridLayoutManager layoutManager = ((GridLayoutManager)recyclerViewSecond.getLayoutManager());
                //int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();

            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                // System.out.println("!!!!!!!!!!!!!!!" + newState);
                // System.out.println();
            }
        };

        recyclerViewSecond.addOnScrollListener(onScrollListener);

        //weatherAdapter.notifyItemRemoved();
        // townList.clear();
        // weatherAdapter.notifyDataSetChanged();
        // recyclerViewSecond.setAdapter(weatherAdapter);
    }


    @Override
    public void onTownClick(View view, int position) {
        int tempSize;
        int tempPosition = 0;
        tempSize = recyclerView.getAdapter().getItemCount();
        System.out.println(tempSize);
        recyclerViewSecond.scrollToPosition(tempPosition);

    }

    @Override
    public void onWeatherClick(View view, int position) {

    }
}