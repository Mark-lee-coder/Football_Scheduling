package com.example.footballscheduling.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footballscheduling.R;
import com.example.footballscheduling.constructors.Teams;
import com.example.footballscheduling.activities.TeamsAndPlayers;
import com.example.footballscheduling.activities.Transfers;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.HashMap;

public class AdapterTeam2 extends RecyclerView.Adapter<AdapterTeam2.MyViewHolder> {
    ArrayList<Teams> mList;
    Context context;
    String key;
    HashMap<String, Object> map;

    public AdapterTeam2(Context context, ArrayList<Teams> mList, String key, HashMap<String, Object> map){
        this.mList = mList;
        this.context = context;
        this.key = key;
        this.map = map;
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
        String Key = teams.getKey();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure you want transfer this player to this team?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Teams").child(Key).child("Players");
                        map.remove("Key");
                        map.remove("key");
                        DatabaseReference reference = databaseReference.push();
                        String playerKey = reference.getKey();
                        map.put("key", playerKey);
                        reference.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(context, "Transfer has been completed!", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(context, TeamsAndPlayers.class);
                                    Activity activity = (Activity) context;
                                    activity.startActivity(intent);
                                }
                                else {
                                    Toast.makeText(context, "Transfer has not been completed!", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "Transfer has not been completed!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(context, Transfers.class);
                        Activity activity = (Activity) context;
                        activity.startActivity(intent);
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