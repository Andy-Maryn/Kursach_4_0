package com.example.kursach_4_0;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.kursach_4_0.adapter.MyAdapter;
import com.example.kursach_4_0.api.MyService;
import com.example.kursach_4_0.api.model.Data;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MyAdapter(this);
        
        MyService.createRetrofit().getData("Odessa", MyService.KEY, "ru").enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {

            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                System.out.println("SUKA");
                System.out.println(t.getMessage());
            }
        });
    }


}