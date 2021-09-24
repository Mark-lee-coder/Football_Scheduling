package com.example.footballscheduling;

import android.content.Context;
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

        holder.round.setText("Round " + (position + 1));

        holder.team1.setText(match1.get("home").toString());
        holder.team2.setText(match1.get("away").toString());
        holder.team3.setText(match2.get("home").toString());
        holder.team4.setText(match2.get("away").toString());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView team1, team2, team3, team4, round;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            team1 = itemView.findViewById(R.id.team1);
            team2 = itemView.findViewById(R.id.team2);
            team3 = itemView.findViewById(R.id.team3);
            team4 = itemView.findViewById(R.id.team4);
            round = itemView.findViewById(R.id.round);
        }
    }
}