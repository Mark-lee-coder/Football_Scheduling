package com.example.footballscheduling;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AdapterTeam2 extends RecyclerView.Adapter<AdapterTeam2.MyViewHolder> {
    ArrayList<Teams> mList;
    Context context;

    public AdapterTeam2(Context context, ArrayList<Teams> mList){
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.team_view1,parent,false);
        return new AdapterTeam2.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Teams teams = mList.get(position);
        holder.TeamName.setText(teams.getTeamName());
        //String key = teams.getKey();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure you want transfer this player to this team?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
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