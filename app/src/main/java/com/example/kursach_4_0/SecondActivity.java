package com.example.kursach_4_0;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kursach_4_0.adapter.MyRecyclerViewAdapter;
import com.example.kursach_4_0.adapter.MySecondRecyclerViewAdapter;
import com.example.kursach_4_0.api.MyService;
import com.example.kursach_4_0.api.model.Data;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity implements MySecondRecyclerViewAdapter.ItemClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        MyRecyclerViewAdapter adapter;

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


    }

}