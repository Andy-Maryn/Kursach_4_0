package com.example.kursach_4_0;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kursach_4_0.adapter.MySecondRecyclerViewAdapter;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity implements MySecondRecyclerViewAdapter.ItemClickListener {

    public static int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        setResult(RESULT_CANCELED);

        MySecondRecyclerViewAdapter adapter;

        // data to populate the RecyclerView with
        ArrayList<String> towns = new ArrayList<>();
        towns.add("London");
        towns.add("Odessa");

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvSeconTowns);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MySecondRecyclerViewAdapter(this, towns);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Пользователь вышел из SecondActivity", Toast.LENGTH_SHORT).show();
            }
            else if (resultCode == RESULT_OK) {
                if (data != null) {
                    String text = data.getStringExtra("result");
                    if (text != null) {
                        System.out.println(text);
                    }
                }
            }
        }
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}