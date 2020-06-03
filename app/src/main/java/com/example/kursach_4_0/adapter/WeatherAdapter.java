package com.example.kursach_4_0.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kursach_4_0.R;
import com.example.kursach_4_0.SecondActivity;

import java.util.List;
import java.util.Date;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    // private List<???> mDataImage;
    private List<String> mDataDescription;
    private List<Float> mDataTemperature;
    private List<Float> mDataWindDegree;
    private List<Float> mDataWindSpeed;
    private List<Date> mDataDate;



    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public WeatherAdapter(Context context,
                          List<String> description,
                          List<Float> temperature,
                          List<Float> degree,
                          List<Float> speed,
                          List<Date> date) {
        this.mInflater = LayoutInflater.from(context);
        this.mDataDescription = description;
        this.mDataTemperature = temperature;
        this.mDataWindDegree = degree;
        this.mDataWindSpeed = speed;
        this.mDataDate = date;
    }

    public void setData(List<String> mData) {
        this.mDataDescription = mData;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycleview_column, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String description = mDataDescription.get(position);
        Float temperature = mDataTemperature.get(position);
        Float windDegree = mDataWindDegree.get(position);
        Float windSpeed = mDataWindSpeed.get(position);
        Date date = mDataDate.get(position);
        holder.myTextViewDescription.setText(description);

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mDataDescription.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextViewDescription;
        TextView myTextViewTemperature;
        TextView myTextViewWindDegree;
        TextView myTextViewWindSpeed;
        TextView myTextViewDate;

        ViewHolder(View itemView) {
            super(itemView);
            myTextViewDescription = itemView.findViewById(R.id.columnTextViewDescription);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onWeatherClick(view, getAdapterPosition());

        }
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return mDataDescription.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(SecondActivity itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onWeatherClick(View view, int position);
    }

    public void handleClick(Context context) {
        Intent intent = new Intent(context, SecondActivity.class);
        context.startActivity(intent);
    }
}
