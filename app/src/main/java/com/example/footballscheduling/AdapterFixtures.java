package com.example.footballscheduling;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Map;

public class AdapterFixtures extends RecyclerView.Adapter<AdapterFixtures.MyViewHolder> {
    ArrayList<ArrayList<Map>> mList;
    Context context;

    public AdapterFixtures(Context context, ArrayList<ArrayList<Map>> mList){
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterFixtures.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.fixture_view, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ArrayList<Map> round = mList.get(position);
        Map<String, Object> match1 = round.get(0);
        Map<String, Object> match2 = round.get(1);
        Map<String, Object> match3 = round.get(2);
        Map<String, Object> match4 = round.get(3);

        holder.round.setText("Round " + (position + 1));

        holder.team1.setText(match1.get("home").toString());
        holder.team2.setText(match1.get("away").toString());
        holder.team3.setText(match2.get("home").toString());
        holder.team4.setText(match2.get("away").toString());
        holder.team5.setText(match3.get("home").toString());
        holder.team6.setText(match3.get("away").toString());
        holder.team7.setText(match4.get("home").toString());
        holder.team8.setText(match4.get("away").toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DisplayTeamFixtures.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView team1, team2, team3, team4, team5, team6, team7, team8, round;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            team1 = itemView.findViewById(R.id.team1);
            team2 = itemView.findViewById(R.id.team2);
            team3 = itemView.findViewById(R.id.team3);
            team4 = itemView.findViewById(R.id.team4);
            team5 = itemView.findViewById(R.id.team5);
            team6 = itemView.findViewById(R.id.team6);
            team7 = itemView.findViewById(R.id.team7);
            team8 = itemView.findViewById(R.id.team8);
            round = itemView.findViewById(R.id.round);
        }
    }
}