package com.example.kursach_4_0;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kursach_4_0.adapter.MyRecyclerViewAdapter;
import com.example.kursach_4_0.api.MyService;
import com.example.kursach_4_0.api.model.Data;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener  {
    // MyAdapter adapter;
    String location = "Odessa";
    public String pos;

    MyRecyclerViewAdapter adapter;

//on create
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // data to populate the RecyclerView with
        ArrayList<String> towns = new ArrayList<>();
        towns.add("London");
        towns.add("Odessa");

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvTowns);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, towns);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        System.out.println(pos);
        location = pos;



        //spinner
        ArrayAdapter<String> spTownAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, towns);
        spTownAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(R.id.cities);
        spinner.setAdapter(spTownAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             /**  adapter.handleClick(this, pos); **/
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
        });
    }
        // adapter = new MyAdapter(this);
        /**
        MyService.createRetrofit().getData("Odessa", MyService.KEY, "ru").enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                System.out.println(response.body().getDayList().get(0).getDate());
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                System.out.println("error");
                System.out.println(t.getMessage());
            }
        });
         **/

    public void myResponse(String location){
        MyService.createRetrofit().getData(location, MyService.KEY, "ru").enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                System.out.println(response.body().getDayList().get(0).getWeather().getId());
                System.out.println(response.body().getDayList().get(0).getDate());
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
        // Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
        pos = adapter.getItem(position);
        //this.myResponse(pos);
        adapter.handleClick(this, pos);
    }

}