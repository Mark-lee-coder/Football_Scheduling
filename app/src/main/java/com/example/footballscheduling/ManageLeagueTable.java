package com.example.footballscheduling;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ManageLeagueTable extends AppCompatActivity {
    private AdapterResults adapterResults;
    private RecyclerView recyclerView;
    private ArrayList<ArrayList<Map>>  list;
    Toolbar toolbar;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ManageLeagueTable.this, Home.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_league_table);

        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerview);

        setSupportActionBar(toolbar);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();

        Query query = FirebaseDatabase.getInstance().getReference().child("Fixtures");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    ArrayList<Map> matches = new ArrayList<>();
                    for (DataSnapshot match : snapshot1.getChildren()){
                        Map<String, Object> map = new HashMap<>();
                        map.put("home", match.child("home").getValue());
                        map.put("away", match.child("away").getValue());
                        matches.add(map);
                    }
                    list.add(matches);
                }
                adapterResults = new AdapterResults(ManageLeagueTable.this, list);
                recyclerView.setAdapter(adapterResults);
                adapterResults.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}