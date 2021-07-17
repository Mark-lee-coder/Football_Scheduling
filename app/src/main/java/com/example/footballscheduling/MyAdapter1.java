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

public class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.MyViewHolder> {
    ArrayList<Model1> mList;
    Context context1;

    public MyAdapter1(Context context1, ArrayList<Model1> mList){
        this.mList = mList;
        this.context1 = context1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context1).inflate(R.layout.activity_view_loans,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model1 model1 = mList.get(position);
        holder.IDNumber.setText(model1.getIDNumber());
        holder.PlayerName.setText(model1.getPlayerName());
        holder.LoanDate.setText(model1.getLoanDate());
        holder.LoanDuration.setText(model1.getLoanDuration());
        holder.LoanFrom.setText(model1.getLoanFrom());
        holder.LoanTo.setText(model1.getLoanTo());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context1);
                builder.setMessage("Are you sure you want to delete this record?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = firebaseDatabase.getReference().child("LoanTransfers").child(model1.getKey());
                        databaseReference.removeValue();
                        Toast.makeText(context1.getApplicationContext(), "Transfer deleted successfully", Toast.LENGTH_LONG).show();
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

    public  static  class  MyViewHolder extends RecyclerView.ViewHolder{

        TextView IDNumber,PlayerName,LoanDate,LoanDuration,LoanFrom,LoanTo;
        ImageView delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            IDNumber = itemView.findViewById(R.id.lId_text);
            PlayerName = itemView.findViewById(R.id.lName_text);
            LoanFrom = itemView.findViewById(R.id.lFrom_text);
            LoanTo = itemView.findViewById(R.id.lTo_text);
            LoanDate = itemView.findViewById(R.id.lDate_text);
            LoanDuration = itemView.findViewById(R.id.lDuration_text);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}