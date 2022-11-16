package com.example.footballscheduling.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.TextView;

import com.example.footballscheduling.models.ModelPlayers;
import com.example.footballscheduling.R;
import com.example.footballscheduling.adapters.MyAdapterPlayers2;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class DisplayPlayers1 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapterPlayers2 adapterPlayers2;
    private ArrayList<ModelPlayers> list;
    Toolbar toolbar;
    TextView teamParsed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_reporting);

        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerview);
        teamParsed = findViewById(R.id.teamParsed);

        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        String TeamName = extras.getString("TeamName");
        teamParsed.setText(TeamName);
        String key = extras.getString("Key");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();

        Query query = FirebaseDatabase.getInstance().getReference().child("Teams").child(key).child("Players");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    ModelPlayers modelPlayers = dataSnapshot.getValue(ModelPlayers.class);
                    list.add(modelPlayers);
                }
                adapterPlayers2 = new MyAdapterPlayers2(DisplayPlayers1.this, list, key);
                recyclerView.setAdapter(adapterPlayers2);
                adapterPlayers2.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}