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

public class MyAdapterPlayers1 extends RecyclerView.Adapter<MyAdapterPlayers1.MyViewHolder> {
    ArrayList<ModelPlayers> mList;
    Context context;

    public MyAdapterPlayers1(Context context, ArrayList<ModelPlayers> mList){
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public MyAdapterPlayers1.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_view_players1, parent, false);
        return new MyAdapterPlayers1.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyAdapterPlayers1.MyViewHolder holder, int position) {
        ModelPlayers modelPlayers = mList.get(position);
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