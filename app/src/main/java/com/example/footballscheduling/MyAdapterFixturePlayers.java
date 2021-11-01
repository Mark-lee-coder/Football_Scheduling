package com.example.footballscheduling;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MyAdapterFixturePlayers extends RecyclerView.Adapter<MyAdapterFixturePlayers.MyViewHolder> {
    ArrayList<ModelPlayers> mList;
    Context context;
    String key;

    public MyAdapterFixturePlayers(Context context, ArrayList<ModelPlayers> mList, String key){
        this.mList = mList;
        this.context = context;
        this.key = key;
    }

    @NonNull
    @Override
    public MyAdapterFixturePlayers.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_view_players2, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterFixturePlayers.MyViewHolder holder, int position) {
        ModelPlayers modelPlayers = mList.get(position);
        holder.PlayerName.setText(modelPlayers.getPlayerName());
        holder.IDNumber.setText(modelPlayers.getIDNumber());
        String Key = modelPlayers.getKey();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView PlayerName, IDNumber;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            PlayerName = itemView.findViewById(R.id.dummyName);
            IDNumber = itemView.findViewById(R.id.dummyID);
        }
    }
}