package com.example.footballscheduling.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footballscheduling.models.ModelPlayers;
import com.example.footballscheduling.R;
import com.example.footballscheduling.activities.Transfers1;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.HashMap;

public class MyAdapterPlayers1 extends RecyclerView.Adapter<MyAdapterPlayers1.MyViewHolder> {
    ArrayList<ModelPlayers> mList;
    Context context;
    String Key;

    public MyAdapterPlayers1(Context context, ArrayList<ModelPlayers> mList, String Key){
        this.mList = mList;
        this.context = context;
        this.Key = Key;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_view_players1, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ModelPlayers modelPlayers = mList.get(position);
        holder.PlayerName.setText(modelPlayers.getPlayerName());
        holder.IDNumber.setText(modelPlayers.getIDNumber());
        String key = modelPlayers.getKey();

        holder.transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure you want to transfer this player from this team? Please note that clicking YES will remove the player from this team permanently! THIS ACTION CANNOT BE UNDONE!");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Teams").child(Key).child("Players").child(key);
                        databaseReference.removeValue();
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("playerName", modelPlayers.getPlayerName());
                        map.put("idNumber", modelPlayers.getIDNumber());
                        map.put("key", key);
                        map.put("Key", Key);
                        Intent intent = new Intent(context, Transfers1.class);
                        intent.putExtra("map", map);
                        context.startActivity(intent);
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

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView PlayerName, IDNumber;
        ImageView transfer;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            PlayerName = itemView.findViewById(R.id.dummyName);
            IDNumber = itemView.findViewById(R.id.dummyID);
            transfer = itemView.findViewById(R.id.Transfer);
        }
    }
}