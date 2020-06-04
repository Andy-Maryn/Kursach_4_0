package com.example.kursach_4_0.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kursach_4_0.R;
import com.example.kursach_4_0.SecondActivity;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
        View view = mInflater.inflate(R.layout.second_recycleview_column, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String description = mDataDescription.get(position);
        String temperature;
        Float temp = Float.valueOf(Math.round((mDataTemperature.get(position)-273.15)*100)/100);
        if (temp>=0){
            temperature = "+" + String.valueOf(temp);
        }
        else {
            temperature = "-" + String.valueOf(temp);
        }
        // String temperature = String.valueOf(temp);
        //String windDegree = String.valueOf(mDataWindDegree.get(position));
        String windSpeed = String.valueOf(mDataWindSpeed.get(position));
        Date date = mDataDate.get(position);

        String windDegree = "";
        int degree = Math.round(mDataWindDegree.get(position)/45);
                switch (degree){
                    case 0:
                        windDegree = "северный";
                        break;
                    case 1:
                        windDegree = "северо-западный";
                        break;
                    case 2:
                        windDegree = "западный";
                        break;
                    case 3:
                        windDegree = "юго-западный";
                        break;
                    case 4:
                        windDegree = "южный";
                        break;
                    case 5:
                        windDegree = "юго-восточный";
                        break;
                    case 6:
                        windDegree = "восточный";
                        break;
                    case 7:
                        windDegree = "северо-восточный";
                        break;
                    case 8:
                        windDegree = "северный";
                        break;
        }


        Format formatter = new SimpleDateFormat("E HH:mm");
        String today = formatter.format(date);

        int image = R.drawable.rainbow;
        switch (description){
            case "ясно":
                image = R.drawable.full_sun;
                break;
            case "переменная облачность":
                image = R.drawable.sun_cloud;
                break;
            case "пасмурно":
                image = R.drawable.cloud;
                break;
            case "дождь":
                image = R.drawable.rain;
                break;
            case "снег":
                image = R.drawable.snow;
                break;
            case "облачно с прояснениями":
                image = R.drawable.cloudy_with_explanations;
                break;
            case "небольшой дождь":
                image = R.drawable.small_rain;
                break;
            case "небольшая облачность":
                image = R.drawable.overcast;
                break;
        }
        holder.myImageViewDescription.setImageResource(image);

        holder.myTextViewDescription.setText(description);
        holder.myTextViewTemperature.setText(temperature);

        holder.myTextViewWindDegree.setText(windDegree);
        holder.myTextViewWindSpeed.setText(windSpeed);
        holder.myTextViewDate.setText(today);

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mDataDescription.size();
    }

    public List<Date> getItemTemp() {
        return mDataDate;
    }



    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextViewDescription;
        TextView myTextViewTemperature;
        TextView myTextViewWindDegree;
        TextView myTextViewWindSpeed;
        TextView myTextViewDate;
        ImageView myImageViewDescription;

        ViewHolder(View itemView) {
            super(itemView);
            myTextViewDescription = itemView.findViewById(R.id.columnTextViewDescription);
            myTextViewTemperature = itemView.findViewById(R.id.columnTextViewTemperature);
            myTextViewWindDegree = itemView.findViewById(R.id.columnTextViewWindDegree);
            myTextViewWindSpeed = itemView.findViewById(R.id.columnTextViewWindSpeed);
            myTextViewDate = itemView.findViewById(R.id.columnTextViewDate);
            myImageViewDescription = itemView.findViewById(R.id.columnImageViewDescription);
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
        //Intent intent = new Intent(context, SecondActivity.class);
        //context.startActivity(intent);
    }
}
