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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity implements MySecondRecyclerViewAdapter.ItemClickListener, MyThirdRecycleViewAdapter.ItemClickListener {


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
        towns.add(name);
        //towns.add("Odessa");

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvSeconTowns);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mySecondRecyclerViewAdapter = new MySecondRecyclerViewAdapter(this, towns);
        mySecondRecyclerViewAdapter.setClickListener(this);
        recyclerView.setAdapter(mySecondRecyclerViewAdapter);

        RecyclerView recyclerViewSecond = findViewById(R.id.rvSeconTowns2);
        myThirdRecycleViewAdapter = new MyThirdRecycleViewAdapter(this, towns);
        myThirdRecycleViewAdapter.setClickListener(this);
        recyclerViewSecond.setAdapter(myThirdRecycleViewAdapter);

        for(int j=0; j < 7; j++)
        myResponse(name, 0);


    }

    public void myResponse(String location, final int i){
        MyService.createRetrofit().getData(location, MyService.KEY, "ru").enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                System.out.println(response.body().getDayList().get(i).getDate());
                System.out.println(response.body().getDayList().get(i).getMainData().getTemperature());
                System.out.println(response.body().getDayList().get(i).getWind().getDegree());
                System.out.println(response.body().getDayList().get(i).getWind().getSpeed());
                //System.out.println(response.body().getDayList().get(i).getWeather().getId());
                System.out.println(response.body().getDayList().get(i).getWeather().getDescription());
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