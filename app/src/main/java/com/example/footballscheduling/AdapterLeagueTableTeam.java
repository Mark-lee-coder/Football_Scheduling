package com.example.footballscheduling;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AdapterLeagueTableTeam extends RecyclerView.Adapter<AdapterLeagueTableTeam.MyViewHolder> {
    ArrayList<Teams> mList;
    Context context;

    public AdapterLeagueTableTeam(Context context, ArrayList<Teams> mList) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterLeagueTableTeam.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.team_view1,parent,false);
        return new AdapterLeagueTableTeam.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Teams teams = mList.get(position);
        holder.TeamName.setText(teams.getTeamName());
        String key = teams.getKey();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public  static  class  MyViewHolder extends RecyclerView.ViewHolder{
        TextView TeamName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TeamName = itemView.findViewById(R.id.teamName);
        }
    }
}