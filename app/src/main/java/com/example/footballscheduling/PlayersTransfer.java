package com.example.footballscheduling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class PlayersTransfer extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AdapterPlayers adapterPlayers;
    private ArrayList<ModelPlayers> list;
    Toolbar toolbar;
    ImageView transfer;
    TextView teamParsed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_transfer);

        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerview);
        teamParsed = findViewById(R.id.teamParsed);
        transfer = findViewById(R.id.transfer);

        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        String TeamName = extras.getString("Team Name");
        teamParsed.setText(TeamName);

        Bundle extras1 = getIntent().getExtras();
        String key = extras1.getString("Key");

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
                adapterPlayers = new AdapterPlayers(PlayersTransfer.this, list);
                recyclerView.setAdapter(adapterPlayers);
                adapterPlayers.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}