package com.example.footballscheduling;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class MyAdapterPlayers1 extends RecyclerView.Adapter<MyAdapterPlayers1.MyViewHolder> {
    ArrayList<ModelPlayers> mList;
    Context context;

    public MyAdapterPlayers1(Context context, ArrayList<ModelPlayers> mList){
        this.mList = mList;
        this.context = context;
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

        /*holder.transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure you want to delete this player? Please note that clicking YES removes this player from this team, this action cannot be undone!");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Teams").child("key").child("Players").child(key);
                        databaseReference.removeValue();
                    }
                });
            }
        });*/
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