package com.example.footballscheduling.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footballscheduling.activities.DisplayFixturePlayers;
import com.example.footballscheduling.R;
import com.example.footballscheduling.constructors.Teams;

import java.util.ArrayList;

public class AdapterTeamFixtures extends RecyclerView.Adapter<AdapterTeamFixtures.MyViewHolder> {
    ArrayList<Teams> mList;
    Context context;

    public AdapterTeamFixtures(Context context, ArrayList<Teams> mList){
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterTeamFixtures.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.team_view1,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTeamFixtures.MyViewHolder holder, int position) {
        Teams teams = mList.get(position);
        holder.TeamName.setText(teams.getTeamName());
        String key = teams.getKey();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DisplayFixturePlayers.class);
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