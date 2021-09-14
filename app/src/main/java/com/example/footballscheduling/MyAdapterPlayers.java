package com.example.footballscheduling;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

public class MyAdapterPlayers extends RecyclerView.Adapter<MyAdapterPlayers.MyViewHolder> {
    ArrayList<ModelPlayers> mList;
    Context context;

    public MyAdapterPlayers(Context context, ArrayList<ModelPlayers> mList){
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public MyAdapterPlayers.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_view_players, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyAdapterPlayers.MyViewHolder holder, int position) {
        ModelPlayers modelPlayers = mList.get(position);
        holder.PlayerName.setText(modelPlayers.getPlayerName());
        holder.IDNumber.setText(modelPlayers.getIDNumber());
        String key = modelPlayers.getKey();

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure you want to delete this player?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Teams").child("key").child("Players").child(key);
                        databaseReference.removeValue();
                        Toast.makeText(context, "The player has been successfully deleted from your team", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(context, DisplayPlayers.class);
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
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView PlayerName, IDNumber;
        ImageView delete;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            PlayerName = itemView.findViewById(R.id.dummyName);
            IDNumber = itemView.findViewById(R.id.dummyID);
            delete = itemView.findViewById(R.id.Delete);
        }
    }
}