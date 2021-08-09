package com.example.footballscheduling;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class AdapterPlayers extends RecyclerView.Adapter<AdapterPlayers.MyViewHolder> {
    ArrayList<ModelPlayers1> mList;
    Context context;

    public AdapterPlayers(Context context, ArrayList<ModelPlayers1> mList){
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public AdapterPlayers.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_transfer_player, parent, false);
        return new AdapterPlayers.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterPlayers.MyViewHolder holder, int position) {
        ModelPlayers1 modelPlayers = mList.get(position);
        holder.PlayerName.setText(modelPlayers.getPlayerName());
        holder.IDNumber.setText(modelPlayers.getIDNumber());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView PlayerName, IDNumber;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            PlayerName = itemView.findViewById(R.id.dummyName);
            IDNumber = itemView.findViewById(R.id.dummyId);
        }
    }
}