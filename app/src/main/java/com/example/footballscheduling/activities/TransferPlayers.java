package com.example.footballscheduling.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.TextView;

import com.example.footballscheduling.R;
import com.example.footballscheduling.adapters.MyAdapterPlayers1;
import com.example.footballscheduling.models.ModelPlayers;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class TransferPlayers extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapterPlayers1 adapterPlayers1;
    private ArrayList<ModelPlayers> list;
    Toolbar toolbar;
    TextView teamParsed;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_players);

        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerview);
        teamParsed = findViewById(R.id.teamParsed);

        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        String TeamName = extras.getString("TeamName");
        teamParsed.setText(TeamName);
        key = extras.getString("Key");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();

        Query query = FirebaseDatabase.getInstance().getReference().child("Teams").child(key).child("Players");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    ModelPlayers modelPlayers = dataSnapshot.getValue(ModelPlayers.class);
                    list.add(modelPlayers);
                }
                adapterPlayers1 = new MyAdapterPlayers1(TransferPlayers.this, list, key);
                recyclerView.setAdapter(adapterPlayers1);
                adapterPlayers1.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}