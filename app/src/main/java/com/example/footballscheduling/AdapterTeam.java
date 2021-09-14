package com.example.footballscheduling;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class AdapterTeam extends RecyclerView.Adapter<AdapterTeam.MyViewHolder> {
    ArrayList<Teams> mList;
    Context context;

    public AdapterTeam(Context context, ArrayList<Teams> mList) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public AdapterTeam.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.team_view,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterTeam.MyViewHolder holder, int position) {
        Teams teams = mList.get(position);
        holder.TeamName.setText(teams.getTeamName());
        String key = teams.getKey();

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure you want to delete this team?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Teams").child(key);
                        databaseReference.removeValue();
                        Toast.makeText(context, "Team deleted successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(context, TeamsAndPlayers.class);
                        context.startActivity(intent);
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DisplayPlayers.class);
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
        ImageView delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TeamName = itemView.findViewById(R.id.teamName);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}