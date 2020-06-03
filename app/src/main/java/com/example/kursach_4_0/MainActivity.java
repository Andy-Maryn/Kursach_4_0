package com.example.kursach_4_0;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
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

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener , PopupMenu.OnMenuItemClickListener {
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

    public void showPopup(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Toast.makeText(this,"item 1 clicked",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2:
                Toast.makeText(this,"item 2 clicked",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                Toast.makeText(this,"item 3 clicked",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item4:
                Toast.makeText(this,"item 4 clicked",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item5:
                Toast.makeText(this,"item 5 clicked",Toast.LENGTH_SHORT).show();
                return true;
            default: return false;
        }
    }

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