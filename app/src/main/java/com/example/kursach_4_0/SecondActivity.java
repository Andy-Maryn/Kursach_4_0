package com.example.kursach_4_0;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.OnScrollListener;

import com.example.kursach_4_0.adapter.TownAdapter;
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

public class SecondActivity extends AppCompatActivity implements TownAdapter.ItemClickListener, WeatherAdapter.ItemClickListener {

    ArrayList<Float> windSpeed = new ArrayList<>();
    ArrayList<Float> windDegree = new ArrayList<>();
    ArrayList<String> weatherDescription = new ArrayList<>();
    ArrayList<Float> mainDataTemperature = new ArrayList<>();
    ArrayList<Date> dataDayList = new ArrayList<>();
    int picselItem = 1;
    int picselSee = 0;
    private static final int picselSize = 10920;
    int currentPosition = 0;

    // GridLayoutManager layoutManager;


    WeatherAdapter weatherAdapter;
    TownAdapter townAdapter;

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
        townAdapter = new TownAdapter(this, dataDayList);




        // data to populate the RecyclerView with
        ArrayList<String> townList = new ArrayList<>();

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
                    townAdapter.setData(dataDayList);
                    weatherAdapter.notifyDataSetChanged();
                    townAdapter.notifyDataSetChanged();
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
        recyclerView = findViewById(R.id.rvTown);
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
                picselItem = picselSize/ Objects.requireNonNull(recyclerViewSecond.getAdapter()).getItemCount();
                picselSee += dx;
                int iterator = 0;
                iterator = Math.round(picselSee/picselItem);
                //System.out.println(iterator);
                List<Date> listData = weatherAdapter.getItemTemp();
                // System.out.println(weatherAdapter.getItemTemp());
                TextView textView = findViewById(R.id.today);
                SimpleDateFormat formatter = new SimpleDateFormat("EEEE   dd MMMM");
                if (iterator<listData.size() && iterator>=0){
                    Date item = listData.get(iterator);
                    String today = formatter.format(item);
                    textView.setText(today);
                    currentPosition = iterator;
                }

                // int index = listData.indexOf(item);



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
        // int tempSize;
        int tempPosition = 0;


        // tempSize = Objects.requireNonNull(recyclerView.getAdapter()).getItemCount();
        //System.out.println(tempSize);

        recyclerViewSecond.scrollToPosition(tempPosition);
        String item = townAdapter.getItem(position);
        //System.out.println(item);

        //System.out.println(townAdapter.getItemString());

        List<Date> listData = weatherAdapter.getItemTemp();
        ArrayList<String> tempList = new ArrayList<String>();
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE -dd MMMM");
        for(Date date: listData){
            String today = formatter.format(date);
            tempList.add(today);
        }
        //System.out.println(tempList);
        int index = tempList.indexOf(item);
        //System.out.println(index);
        int iterator = 0;


        picselItem = picselSize/ Objects.requireNonNull(recyclerViewSecond.getAdapter()).getItemCount();
        iterator = Math.round(picselSee/picselItem);

        //System.out.println(index);
        //System.out.println(iterator);
        if (index >iterator){
            recyclerViewSecond.scrollToPosition(index+2);
            picselSee = picselItem*(index+2);
            int tempIter = 0;
            System.out.println(picselSee);
            if (picselSee>picselSize){
                int temp = picselSee - picselSize;
                //System.out.println(temp);
                tempIter = Math.round(((temp+1) / picselItem));
                //System.out.println(tempIter);
                recyclerViewSecond.scrollToPosition(index+2 - 2/tempIter);
                picselSee = picselItem*(index+2 - 2/tempIter);
            }

            //System.out.println(picselSee);
        }
        else {
            recyclerViewSecond.scrollToPosition(index);
            picselSee = picselItem*(index);
            //System.out.println("!!!!!!!!!!!!!!!!!!!");
        }
        currentPosition = index;
        //TextView textView = findViewById(R.id.today);
        //textView.setText(String.valueOf(item));

        //recyclerViewSecond.scrollToPosition(index+2);


    }

    @Override
    public void onWeatherClick(View view, int position) {

    }
}