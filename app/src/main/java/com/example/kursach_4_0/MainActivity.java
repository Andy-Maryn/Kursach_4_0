package com.example.kursach_4_0;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
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

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {
    // MyAdapter adapter;
    String location = "Odessa";
    public String pos;
    Context context = this;

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
        towns.add("123");

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvTowns);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, towns);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        System.out.println(pos);
        location = pos;





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
    }


    public void myResponse(String location, View view, int position){
        MyService.createRetrofit().getData(location, MyService.KEY, "ru").enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                System.out.println(response.body().getDayList().get(0).getWeather().getId());
                System.out.println(response.body().getDayList().get(0).getDate());
                adapter.handleClick(context, pos);
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                System.out.println("error");
                System.out.println(t.getMessage());
                Toast.makeText(context, "ypor Город " + pos + " не найден", Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public void onItemClick(View view, int position) {
        // Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show()
        pos = adapter.getItem(position);
        try {
            float number = Float.valueOf(pos);
            Toast.makeText(this, "Город " + pos + " не найден", Toast.LENGTH_SHORT).show();
            //ststus = false;
        }
        catch (Exception ex){
            // Toast.makeText(this, "Город " + pos + " не найден", Toast.LENGTH_SHORT).show();
            // ststus = false;
            this.myResponse(pos, view, position);

            // this.myResponse(pos);

            //adapter.handleClick(this, pos, status);
                //ststus = new Boolean(false) ;
            }
        }


}