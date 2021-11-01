package com.example.footballscheduling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DisplayTeamFixtures extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    private AdapterTeamFixtures adapterTeamFixtures;
    private ArrayList<Teams> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_team_fixtures);

        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerview);

        setSupportActionBar(toolbar);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        list = new ArrayList<>();

        Query query = FirebaseDatabase.getInstance().getReference().child("Teams");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Teams model = dataSnapshot.getValue(Teams.class);
                    list.add(model);
                }
                adapterTeamFixtures = new AdapterTeamFixtures(DisplayTeamFixtures.this, list);
                recyclerView.setAdapter(adapterTeamFixtures);
                adapterTeamFixtures.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}