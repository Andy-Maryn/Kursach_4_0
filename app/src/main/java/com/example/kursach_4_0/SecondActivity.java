package com.example.kursach_4_0;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kursach_4_0.adapter.MySecondRecyclerViewAdapter;
import com.example.kursach_4_0.adapter.MyThirdRecycleViewAdapter;
import com.example.kursach_4_0.api.MyService;
import com.example.kursach_4_0.api.model.Data;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity implements MySecondRecyclerViewAdapter.ItemClickListener, MyThirdRecycleViewAdapter.ItemClickListener {

    ArrayList<Float> windSpeed = new ArrayList<>();
    ArrayList<Float> windDegree = new ArrayList<>();
    ArrayList<String> weatherDescription = new ArrayList<>();
    ArrayList<Float> mainDataTemperature = new ArrayList<>();
    ArrayList<Date> dataDayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle arguments = getIntent().getExtras();
        String name = arguments.get("return").toString();

        MySecondRecyclerViewAdapter mySecondRecyclerViewAdapter;
        MyThirdRecycleViewAdapter myThirdRecycleViewAdapter;

        // data to populate the RecyclerView with
        ArrayList<String> towns = new ArrayList<>();

        for(int j=0; j < 7; j++){
            myResponse(name, j);
        }
        System.out.println(weatherDescription);


        towns.add(name);
        towns.add("qwe");
        towns.add("qwe");
        towns.add("wer");
        towns.add("ert");
        towns.add("rty");
        //towns.add("Odessa");

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvSeconTowns);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mySecondRecyclerViewAdapter = new MySecondRecyclerViewAdapter(this, towns);
        mySecondRecyclerViewAdapter.setClickListener(this);
        recyclerView.setAdapter(mySecondRecyclerViewAdapter);

        RecyclerView recyclerViewSecond = findViewById(R.id.rvSeconTowns2);
        recyclerViewSecond.setLayoutManager(new LinearLayoutManager(this));
        myThirdRecycleViewAdapter = new MyThirdRecycleViewAdapter(this, weatherDescription);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewSecond.setLayoutManager(llm);
        myThirdRecycleViewAdapter.setClickListener(this);
        recyclerViewSecond.setAdapter(myThirdRecycleViewAdapter);

        //myThirdRecycleViewAdapter.notifyItemRemoved();
        // towns.clear();
        myThirdRecycleViewAdapter.notifyDataSetChanged();
        recyclerViewSecond.setAdapter(myThirdRecycleViewAdapter);



        for(int j=0; j < 7; j++)
        myResponse(name, j);
    }

    public void myResponse(String location, final int i){
        MyService.createRetrofit().getData(location, MyService.KEY, "ru").enqueue(new Callback<Data>() {
            @Override
            public void onResponse( Call<Data> call, Response<Data> response) {
                System.out.println(response.body().getDayList().get(i).getDate());
                dataDayList.add(response.body().getDayList().get(i).getDate());

                System.out.println(response.body().getDayList().get(i).getMainData().getTemperature());
                mainDataTemperature.add(response.body().getDayList().get(i).getMainData().getTemperature());

                System.out.println(response.body().getDayList().get(i).getWind().getDegree());
                windDegree.add(response.body().getDayList().get(i).getWind().getDegree());

                System.out.println(response.body().getDayList().get(i).getWind().getSpeed());
                windSpeed.add(response.body().getDayList().get(i).getWind().getSpeed());
                //System.out.println(response.body().getDayList().get(i).getWeather().getId());

                System.out.println(response.body().getDayList().get(i).getWeather().getDescription());
                weatherDescription.add(response.body().getDayList().get(i).getWeather().getDescription());
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                System.out.println("error");
                System.out.println(t.getMessage());
            }
        });
    }


    @Override
    public void onItemClick(View view, int position) {

    }
}