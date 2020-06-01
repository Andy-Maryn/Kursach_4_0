package com.example.kursach_4_0.adapter;

import android.content.Context;
import android.content.Intent;

import com.example.kursach_4_0.MainActivity;

public class MyAdapter {
    Context context;

    public MyAdapter(Context context) {
        this.context = context;
    }

    private void newActvityOpen() {
        context.startActivity(new Intent(context, MainActivity.class));
    }
}
