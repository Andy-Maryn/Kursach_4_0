package com.example.kursach_4_0.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kursach_4_0.NewMain;
import com.example.kursach_4_0.R;
import com.example.kursach_4_0.SecondActivity;
import com.example.kursach_4_0.api.MyService;
import com.example.kursach_4_0.api.model.Data;
import com.example.kursach_4_0.fragment.InternationalFragment;
import com.example.kursach_4_0.orm.DatabaseHandler;
import com.example.kursach_4_0.orm.MyTown;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// @AllArgsConstructor
public class MainTownAdapter extends RecyclerView.Adapter<MainTownAdapter.ViewHolder> {

    private List<String> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;
    private String pos;

    private NotesAdapterListener mListener;

    private boolean status;

    // data is passed into the constructor
    public MainTownAdapter(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
        this.status = false;

    }
    public MainTownAdapter(Context context, List<String> data, boolean status) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
        this.status = status;

    }

    public interface NotesAdapterListener {
        void onNoteDelete(MyTown myTown);
    }

    // inflates the row layout from xml when needed
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view;
        if (status){
            view = mInflater.inflate(R.layout.main_favorite_recyclerview_row, parent, false);
        }
        else {
            view = mInflater.inflate(R.layout.main_recyclerview_row, parent, false);
        }

        return new ViewHolder(view);
    }



    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String town = mData.get(position);
        holder.myTextView.setText(town);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                pos = getItem(position);
                try {
                    Float.parseFloat(pos);
                    Toast.makeText(context, "Город " + pos + " не найден", Toast.LENGTH_SHORT).show();
                }
                catch (Exception ex){
                    myResponse(pos, context);
                }
                //Intent intent = new Intent(context, DetailActivity.class);
                //intent.putExtra("dataSet", dataModel.get(position));
                //ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(context, holder.image_view, "robot");
                //context.startActivity(intent);
            }

        });

        if (status){
            holder.delete.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {

                    // listener.onNoteDelete();

                    DatabaseHandler db = new DatabaseHandler(context);

                    for (MyTown myTown : db.getAllMyTown()) {
                        if (town.equals(myTown.getName())) {

                            if (mListener != null) mListener.onNoteDelete(myTown);
                            //db.deleteMyTown(myTown);
                            //this.notifyDataSetChanged();
                        }
                    }
                }

                //updateData(db.getAllMyTown());
                //view.setBackgroundResource(R.drawable.like);

            });
        }
    }

    public void updateData(List<String> data){
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }




    public static void myResponse(final String location, final Context context){
        MyService.createRetrofit().getData(location, MyService.KEY, "ru").enqueue(new Callback<Data>() {
            @Override
            public void onResponse(@NotNull Call<Data> call, @NotNull Response<Data> response) {
                if (response.body() != null) {
                    System.out.println(response.body().getDayList().get(0).getWeather().getId());
                    System.out.println(response.body().getDayList().get(0).getDate());
                    MainTownAdapter.handleClick(context, location);
                } else {
                    Toast.makeText(context, "Город " + location + " не найден", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<Data> call, @NotNull Throwable t) {
                System.out.println("error");
                System.out.println(t.getMessage());
                Toast.makeText(context, "Ошибка сервера", Toast.LENGTH_SHORT).show();
            }

        });
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }



    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        View itemView;
        Button delete;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.tvTownName);
            Button button = (Button) itemView.findViewById(R.id.favoriteButton);


            itemView.setOnClickListener(this);
            delete = button;
            this.itemView = itemView;

        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());

        }
    }


    // convenience method for getting data at click position
    public String getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(NewMain itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public void setListener(InternationalFragment itemListener) {
        this.mListener = itemListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public static void handleClick(Context context, String pos) {

        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra("return", pos);
        context.startActivity(intent);
    }
}
