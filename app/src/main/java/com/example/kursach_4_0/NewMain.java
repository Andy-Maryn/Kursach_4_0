package com.example.kursach_4_0;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.kursach_4_0.adapter.MainTownAdapter;
import com.example.kursach_4_0.adapter.PackageTabAdapter;
import com.example.kursach_4_0.orm.DatabaseHandler;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputLayout;

public class NewMain extends AppCompatActivity{
    private String TAG = "PackageActivity";
    private Context context;

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    private PackageTabAdapter adapter;
    String pos;
    boolean status;

    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_package);
        context = this;

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(2);
        tabLayout = (TabLayout) findViewById(R.id.packagetablayout);
        status = false;

        //createTabFragment();


        db = new DatabaseHandler(this);
        //db.addMyTown(new MyTown("806800000"));
        // System.out.println("!!!" + db.getAllMyTown());

        //db.deleteAll();

    }

    @Override
    protected void onStart() {
        super.onStart();
        createTabFragment();
        TextInputLayout textInputLayout = findViewById(R.id.textInputLayout3);
        textInputLayout.getEditText().setText("");



    }


    private void createTabFragment(){
        adapter = new PackageTabAdapter(getSupportFragmentManager(), tabLayout);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        System.out.println(keyCode);

        if (keyCode == 4)
            finish();

        if (keyCode == 66)
            clickButton();

        return false;
    }

    public void clickButton (){
        // System.out.println("start");
        TextInputLayout textInputLayout = findViewById(R.id.textInputLayout3);

        try {
            EditText text = textInputLayout.getEditText();
            pos = String.valueOf(text.getText());
            if (pos.length() == 0){
                Toast.makeText(this, "Вы не ввели название города", Toast.LENGTH_SHORT).show();
            }
            else {
                Float.parseFloat(pos);
                Toast.makeText(this, "Город " + pos + " не найден", Toast.LENGTH_SHORT).show();
            }

            //ststus = false;
        }
        catch (Exception ex){
            EditText text = textInputLayout.getEditText();
            pos = String.valueOf(text.getText());
            MainTownAdapter.myResponse(pos, this);
            // this.myResponse(pos);
        }
    }

    public void onClickButton(View view){
        clickButton();
    }


    /*

    public void myResponse(String location){
        MyService.createRetrofit().getData(location, MyService.KEY, "ru").enqueue(new Callback<DataDate>() {
            @Override
            public void onResponse(@NotNull Call<DataDate> call, @NotNull Response<DataDate> response) {
                if (response.body() != null) {
                    //System.out.println(response.body().getDayList().get(0).getWeather().getId());
                    //System.out.println("++++++++++++++++++++"+response.body().getDayList().get(0).getDate());
                    MainTownAdapter.handleClick(context, pos);
                } else {
                    Toast.makeText(context, "Город " + pos + " не найден", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<DataDate> call, @NotNull Throwable t) {
                System.out.println("error");
                System.out.println(t.getMessage());
                Toast.makeText(context, "Ошибка сервера", Toast.LENGTH_SHORT).show();
            }

        });
    }

     */


/*
    @Override
    public void onItemClick(View view, int position) {
        //view.getTooltipText();
        String pos = (String) adapter.getPageTitle(0);
        try {
            if (pos.length() == 0){
                Toast.makeText(this, "Вы не ввели название города", Toast.LENGTH_SHORT).show();
            }
            else {
                Float.parseFloat(pos);
                Toast.makeText(this, "Город " + pos + " не найден", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception ex){
            this.myResponse(pos);
        }
    }
*/



}
