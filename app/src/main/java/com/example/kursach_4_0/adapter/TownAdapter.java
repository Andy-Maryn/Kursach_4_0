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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public class TownAdapter extends RecyclerView.Adapter<TownAdapter.ViewHolder> {

    private List<String> mDataHashSet;
    private List<String> mDataString;
    private List<Date> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    private SimpleDateFormat formatter;
    // HashSet<Date> dateHashSet = new HashSet<>();

    // data is passed into the constructor
    public TownAdapter(Context context, ArrayList<Date> data) {
        this.formatter =  new SimpleDateFormat("EEEE -dd MMMM");
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        // this.mDatatemp = new HashSet <String>();
        LinkedHashSet<String> datatemp = new LinkedHashSet<String>();
        // Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        for(Date date: this.mData){
            String today = this.formatter.format(date);
            this.mDataString.add(String.valueOf(date));
            datatemp.add(today);
        }
        this.mDataHashSet = new ArrayList<String>(datatemp);
        // String[] tempArrey = {};
        // tempArrey = this.mDatatemp.toArray(new String[this.mDatatemp.size()]);

    }

    public void setData(ArrayList<Date> mData) {
        this.mData = mData;
        LinkedHashSet <String> datatemp = new LinkedHashSet<String>();
        //Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        for(Date date: this.mData){
            String today =  this.formatter.format(date);
            datatemp.add(today);
        }
        this.mDataHashSet = new ArrayList<String>(datatemp);
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.second_recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HashSet<String> datatemp = new HashSet <String>();
        //Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        // Date date = mData.get(position);
        // Format formatter = new SimpleDateFormat("yyyy-MM-dd");

        // Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        /*
        for(String today: mDatatemp){
            holder.myTextView.setText(today);
        }
        */
        String today = mDataHashSet.get(position);
        holder.myTextView.setText(today);
        // String today = mDatatemp.get(position);

        //  holder.myTextView.setText(today);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mDataHashSet.size();
    }



    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.tvTownName);
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

    public void handleClick(Context context) {
        Intent intent = new Intent(context, SecondActivity.class);
        context.startActivity(intent);
    }
}
