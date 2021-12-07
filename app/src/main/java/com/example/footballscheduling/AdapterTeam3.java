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

public class AdapterTeam3 extends RecyclerView.Adapter<AdapterTeam3.MyViewHolder>{
    ArrayList<Teams> mList;
    Context context;

    public AdapterTeam3(Context context, ArrayList<Teams> mList) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterTeam3.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.team_view1,parent,false);
        return new AdapterTeam3.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTeam3.MyViewHolder holder, int position) {
        Teams teams = mList.get(position);
        holder.TeamName.setText(teams.getTeamName());
        String key = teams.getKey();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DisplayPlayers1.class);
                intent.putExtra("TeamName", teams.getTeamName());
                intent.putExtra("Key", key);
                Activity activity = (Activity) context;
                activity.startActivity(intent);
            }
        });
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