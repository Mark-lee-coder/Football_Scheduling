package com.example.footballscheduling;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList<Model> mList;
    Context context;

    public MyAdapter(Context context, ArrayList<Model> mList){
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_view_permanent,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model model = mList.get(position);
        holder.IDNumber.setText(model.getIDNumber());
        holder.PlayerName.setText(model.getPlayerName());
        holder.TransferDate.setText(model.getTransferDate());
        holder.TransferFees.setText(model.getTransferFees());
        holder.TransferFrom.setText(model.getTransferFrom());
        holder.TransferTo.setText(model.getTransferTo());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure you want to delete this record?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = firebaseDatabase.getReference().child("PermanentTransfers").child(model.getKey());
                        databaseReference.removeValue();
                        Toast.makeText(context.getApplicationContext(), "Transfer deleted successfully", Toast.LENGTH_LONG).show();
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

    public static class  MyViewHolder extends RecyclerView.ViewHolder {

        TextView IDNumber,PlayerName,TransferDate,TransferFees,TransferFrom,TransferTo;
        ImageView delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            IDNumber = itemView.findViewById(R.id.id_text);
            PlayerName = itemView.findViewById(R.id.name_text);
            TransferDate = itemView.findViewById(R.id.date_text);
            TransferFees = itemView.findViewById(R.id.fees_text);
            TransferFrom = itemView.findViewById(R.id.from_text);
            TransferTo = itemView.findViewById(R.id.to_text);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}