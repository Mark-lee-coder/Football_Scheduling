package com.example.footballscheduling.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footballscheduling.models.ModelPlayers;
import com.example.footballscheduling.R;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class MyAdapterPlayers2 extends RecyclerView.Adapter<MyAdapterPlayers2.MyViewHolder>{
    ArrayList<ModelPlayers> mList;
    Context context;
    String Key;

    public MyAdapterPlayers2(Context context, ArrayList<ModelPlayers> mList, String Key){
        this.mList = mList;
        this.context = context;
        this.Key = Key;
    }

    @NonNull
    @Override
    public MyAdapterPlayers2.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_view_players2, parent, false);
        return new MyAdapterPlayers2.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterPlayers2.MyViewHolder holder, int position) {
        ModelPlayers modelPlayers = mList.get(position);
        holder.PlayerName.setText(modelPlayers.getPlayerName());
        holder.IDNumber.setText(modelPlayers.getIDNumber());
        String key = modelPlayers.getKey();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView PlayerName, IDNumber;
        ImageView transfer;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            PlayerName = itemView.findViewById(R.id.dummyName);
            IDNumber = itemView.findViewById(R.id.dummyID);
            transfer = itemView.findViewById(R.id.Transfer);
        }
    }
}