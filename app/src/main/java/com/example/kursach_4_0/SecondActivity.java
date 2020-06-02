package com.example.kursach_4_0;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kursach_4_0.adapter.MySecondRecyclerViewAdapter;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity implements MySecondRecyclerViewAdapter.ItemClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        Bundle arguments = getIntent().getExtras();
        String name = arguments.get("return").toString();

        MySecondRecyclerViewAdapter adapter;

        // data to populate the RecyclerView with
        ArrayList<String> towns = new ArrayList<>();
        towns.add(name);
        //towns.add("Odessa");

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvSeconTowns);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MySecondRecyclerViewAdapter(this, towns);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);


    }



    @Override
    public void onItemClick(View view, int position) {

    }
}