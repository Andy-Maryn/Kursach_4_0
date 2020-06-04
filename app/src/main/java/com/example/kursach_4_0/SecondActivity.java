package com.example.kursach_4_0;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.OnScrollListener;

import com.example.kursach_4_0.adapter.DayWeekAdapter;
import com.example.kursach_4_0.adapter.WeatherAdapter;
import com.example.kursach_4_0.api.MyService;
import com.example.kursach_4_0.api.model.Data;
import com.example.kursach_4_0.api.model.Day;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity implements DayWeekAdapter.ItemClickListener, WeatherAdapter.ItemClickListener {

    ArrayList<Float> windSpeed = new ArrayList<>();
    ArrayList<Float> windDegree = new ArrayList<>();
    ArrayList<String> weatherDescription = new ArrayList<>();
    ArrayList<Float> mainDataTemperature = new ArrayList<>();
    ArrayList<Date> dataDayList = new ArrayList<>();
    int picselItem = 1;
    int picselSee = 0;
    private static final int picselSize = 10920;
    int currentPosition = 0;

    WeatherAdapter weatherAdapter;
    DayWeekAdapter dayWeekAdapter;

    RecyclerView recyclerView;
    RecyclerView recyclerViewSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle arguments = getIntent().getExtras();
        assert arguments != null;
        final String name = Objects.requireNonNull(arguments.get("return")).toString();
        System.out.println(name);

        weatherAdapter = new WeatherAdapter(this,
                weatherDescription,
                mainDataTemperature,
                windDegree,
                windSpeed,
                dataDayList);
        dayWeekAdapter = new DayWeekAdapter(this, dataDayList);

        MyService.createRetrofit().getData(name, MyService.KEY, "ru").enqueue(new Callback<Data>() {
            @Override
            public void onResponse(@NotNull Call<Data> call, @NotNull Response<Data> response) {
                assert response.body() != null;
                for (Day day : response.body().getDayList()) {
                    dataDayList.add(day.getDate());
                    mainDataTemperature.add(day.getMainData().getTemperature());
                    windDegree.add(day.getWind().getDegree());
                    windSpeed.add(day.getWind().getSpeed());
                    weatherDescription.add(day.getWeather().getDescription());

                    weatherAdapter.setData(weatherDescription);
                    dayWeekAdapter.setData(dataDayList);
                    weatherAdapter.notifyDataSetChanged();
                    dayWeekAdapter.notifyDataSetChanged();
                }
                TextView textView = findViewById(R.id.town);
                textView.setText(name);
            }
            @Override
            public void onFailure(@NotNull Call<Data> call, @NotNull Throwable t) {
                System.out.println("error");
                System.out.println(t.getMessage());
            }
        });

        recyclerView = findViewById(R.id.rvTown);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        dayWeekAdapter.setClickListener(this);

        recyclerView.setAdapter(dayWeekAdapter);

        recyclerViewSecond = findViewById(R.id.recyclerWeather);
        recyclerViewSecond.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        weatherAdapter.setClickListener(this);

        recyclerViewSecond.setAdapter(weatherAdapter);

        final OnScrollListener onScrollListener = new OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                picselItem = picselSize/ Objects.requireNonNull(recyclerViewSecond.getAdapter()).getItemCount();
                picselSee += dx;
                int iterator;
                iterator = Math.round(picselSee/picselItem);
                List<Date> listData = weatherAdapter.getItemTemp();
                TextView textView = findViewById(R.id.today);
                SimpleDateFormat formatter = new SimpleDateFormat("EEEE   dd MMMM");
                if (iterator<listData.size() && iterator>=0){
                    Date item = listData.get(iterator);
                    String today = formatter.format(item);
                    textView.setText(today);
                    currentPosition = iterator;
                }
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        };
        recyclerViewSecond.addOnScrollListener(onScrollListener);
    }


    @Override
    public void onTownClick(View view, int position) {
        int tempPosition = 0;
        recyclerViewSecond.scrollToPosition(tempPosition);
        String item = dayWeekAdapter.getItem(position);

        List<Date> listData = weatherAdapter.getItemTemp();
        ArrayList<String> tempList = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE -dd MMMM");
        for(Date date: listData){
            String today = formatter.format(date);
            tempList.add(today);
        }
        int index = tempList.indexOf(item);
        int iterator;


        picselItem = picselSize/ Objects.requireNonNull(recyclerViewSecond.getAdapter()).getItemCount();
        iterator = Math.round(picselSee/picselItem);
        if (index >iterator){
            recyclerViewSecond.scrollToPosition(index+2);
            picselSee = picselItem*(index+2);
            int tempIter;
            System.out.println(picselSee);
            if (picselSee>picselSize){
                int temp = picselSee - picselSize;
                tempIter = Math.round(((temp+1) / picselItem));
                recyclerViewSecond.scrollToPosition(index+2 - 2/tempIter);
                picselSee = picselItem*(index+2 - 2/tempIter);
            }
        }
        else {
            recyclerViewSecond.scrollToPosition(index);
            picselSee = picselItem*(index);
        }
        currentPosition = index;
    }

    @Override
    public void onWeatherClick(View view, int position) {

    }
}