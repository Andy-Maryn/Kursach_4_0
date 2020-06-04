package com.example.kursach_4_0.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kursach_4_0.R;
import com.example.kursach_4_0.SecondActivity;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

public class DayWeekAdapter extends RecyclerView.Adapter<DayWeekAdapter.ViewHolder> {

    private List<String> mDataHashSet;
    private ArrayList<String> mDataString = new ArrayList<>();
    private List<Date> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private List<String> mWeekDayString;

    private SimpleDateFormat formatter;

    // data is passed into the constructor
    public DayWeekAdapter(Context context, ArrayList<Date> data) {
        this.formatter =  new SimpleDateFormat("EEEE -dd MMMM");
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        LinkedHashSet<String> datatemp = new LinkedHashSet<>();
        LinkedHashSet<String> WeekDayString = new LinkedHashSet<>();
        for(Date date: this.mData){
            String today = this.formatter.format(date);
            this.mDataString.add(today);
            datatemp.add(today);

            SimpleDateFormat myFormatter = new SimpleDateFormat("EEEE");
            today = myFormatter.format(date);
            WeekDayString.add(today);

        }
        this.mDataHashSet = new ArrayList<>(datatemp);
        this.mWeekDayString = new ArrayList<>(WeekDayString);
    }

    public void setData(ArrayList<Date> mData) {
        this.mData = mData;
        LinkedHashSet <String> datatemp = new LinkedHashSet<>();
        LinkedHashSet<String> WeekDayString = new LinkedHashSet<>();
        this.mWeekDayString = new ArrayList<>();
        for(Date date: this.mData){
            String today = this.formatter.format(date);
            this.mDataString.add(today);
            datatemp.add(today);

            SimpleDateFormat myFormatter = new SimpleDateFormat("EEEE");
            today = myFormatter.format(date);
            WeekDayString.add(today);
        }
        this.mDataHashSet = new ArrayList<>(datatemp);
        this.mWeekDayString = new ArrayList<>(WeekDayString);
    }

    // inflates the row layout from xml when needed
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.second_recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String today = mDataHashSet.get(position);
        holder.myTextView.setText(today);

        today = mWeekDayString.get(position);
        int roundrect;
        switch (today){
            case "понедельник":
                roundrect = R.drawable.monday2;
                break;
            case "вторник":
                roundrect = R.drawable.tuesday2;
                break;
            case "среда":
                roundrect = R.drawable.wednesday2;
                break;
            case "четверг":
                roundrect = R.drawable.thursday2;
                break;
            case "пятница":
                roundrect = R.drawable.friday2;
                break;
            case "суббота":
                roundrect = R.drawable.saturday2;
                break;
            case "воскресенье":
                roundrect = R.drawable.sunday2;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + today);
        }

        holder.myView.setBackgroundResource(roundrect);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mDataHashSet.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        View myView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.tvTownName);
            myView = itemView.findViewById(R.id.view3);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onTownClick(view, getAdapterPosition());

        }
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return mDataHashSet.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(SecondActivity itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onTownClick(View view, int position);
    }

}
