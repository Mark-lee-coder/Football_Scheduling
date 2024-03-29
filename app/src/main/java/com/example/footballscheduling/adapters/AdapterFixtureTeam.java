package com.example.footballscheduling.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footballscheduling.R;
import com.example.footballscheduling.constructors.Teams;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class AdapterFixtureTeam extends RecyclerView.Adapter<AdapterTeam.MyViewHolder> {
    ArrayList<Teams> mList;
    Context context;

    public AdapterFixtureTeam(Context context, ArrayList<Teams> mList) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public AdapterTeam.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.team_view1,parent,false);
        return new AdapterTeam.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterTeam.MyViewHolder holder, int position) {
        Teams teams = mList.get(position);
        holder.TeamName.setText(teams.getTeamName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public  static  class  MyViewHolder extends RecyclerView.ViewHolder {

        TextView TeamName;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            TeamName = itemView.findViewById(R.id.teamName);
        }
    }
}