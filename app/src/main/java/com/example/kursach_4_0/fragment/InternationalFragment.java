package com.example.kursach_4_0.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kursach_4_0.R;
import com.example.kursach_4_0.adapter.MainTownAdapter;
import com.example.kursach_4_0.orm.DatabaseHandler;
import com.example.kursach_4_0.orm.MyTown;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class InternationalFragment extends Fragment implements MainTownAdapter.NotesAdapterListener {
    private Context context;
    private RecyclerView recyclerView;
    MainTownAdapter mainTownAdapter;
    private ArrayList<String> towns = new ArrayList<>();


    DatabaseHandler db;


    private Executor executor = Executors.newSingleThreadExecutor();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_international, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.context = getContext();
        recyclerView = view.findViewById(R.id.recyclerView);
        this.db = new DatabaseHandler(this.context);
        populateData();
        mainTownAdapter = new MainTownAdapter(context, towns, true, this);
        recyclerView.setAdapter(mainTownAdapter);


        //this.db = new DatabaseHandler(this.context);
        //this.db = Room.databaseBuilder(context, AppDatabase.class, "database").build();

    }


    private void populateData() {

        List<MyTown> myTowns = db.getAllMyTown();
        for (MyTown town: myTowns){
            towns.add(town.getName());
        }


        //MyTown myTown = new MyTown("qwe");
        //List<MyTown> myTowns = new ArrayList<>(db.townDAO().getAll());

        //updateData();

        /*
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

         */
    }

    private void updateData() {
        towns.clear();
        List<MyTown> myTowns = db.getAllMyTown();
        for (MyTown town: myTowns){
            towns.add(town.getName());
        }
        recyclerView.setAdapter(mainTownAdapter);
        mainTownAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNoteDelete(MyTown myTown) {
        db.deleteMyTown(myTown);
        updateData();
    }
}
