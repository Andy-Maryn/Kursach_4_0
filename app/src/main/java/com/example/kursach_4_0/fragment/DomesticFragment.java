package com.example.kursach_4_0.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kursach_4_0.R;
import com.example.kursach_4_0.adapter.MainTownAdapter;
import com.example.kursach_4_0.api.MyService;
import com.example.kursach_4_0.api.model.Data;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DomesticFragment extends Fragment implements MainTownAdapter.ItemClickListener{
    private Context context;
    private RecyclerView recyclerView;
    private MainTownAdapter mainTownAdapter;
    private ArrayList<String> towns = new ArrayList<String>();
    public String pos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_domestic, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.context = getContext();
        recyclerView = view.findViewById(R.id.recyclerView);
        populateData();
        mainTownAdapter = new MainTownAdapter(context, towns);
        recyclerView.setAdapter(mainTownAdapter);

    }

    public String getItem(int id) {
        return mainTownAdapter.getItem(id);
    }

    private void populateData() {
        towns.add("Лондон");
        towns.add("Винница");
        towns.add("Запорожье");
        towns.add("Луганск");
        towns.add("Николаев");
        towns.add("Сумы");
        towns.add("Херсон");
        towns.add("Днепр");
        towns.add("Ивано-Франковск");
        towns.add("Луцк");
        towns.add("Одесса");
        towns.add("Тернополь");
        towns.add("Хмельницкий");
        towns.add("Донецк");
        towns.add("Киев");
        towns.add("Львов");
        towns.add("Полтава");
        towns.add("Ужгород");
        towns.add("Черкассы");
        towns.add("Житомир");
        towns.add("Кривой Рог");
        towns.add("Мариуполь");
        towns.add("Ровно");
        towns.add("Харьков");
        towns.add("Чернигов");
    }

    public void myResponse(String location){
        MyService.createRetrofit().getData(location, MyService.KEY, "ru").enqueue(new Callback<Data>() {
            @Override
            public void onResponse(@NotNull Call<Data> call, @NotNull Response<Data> response) {
                if (response.body() != null) {
                    System.out.println(response.body().getDayList().get(0).getWeather().getId());
                    System.out.println(response.body().getDayList().get(0).getDate());
                    mainTownAdapter.handleClick(getActivity(), pos);
                } else {
                    Toast.makeText(getActivity(), "Город " + pos + " не найден", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<Data> call, @NotNull Throwable t) {
                System.out.println("error");
                System.out.println(t.getMessage());
                Toast.makeText(getActivity(), "Ошибка сервера", Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public void onItemClick(View view, int position) {
        String pos = mainTownAdapter.getItem(position);
        try {
            Float.parseFloat(pos);
            Toast.makeText(getActivity(), "Город " + pos + " не найден", Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex){
            this.myResponse(pos);
        }
    }
}
