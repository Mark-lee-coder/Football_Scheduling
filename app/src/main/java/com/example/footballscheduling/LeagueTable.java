package com.example.footballscheduling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class LeagueTable extends AppCompatActivity {
    Toolbar toolbar;
    Button button;
    TextView team1,team2,team3,team4,team5,team6,team7,team8, team9,team10;
    private ArrayList<Teams> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_table);

        toolbar = findViewById(R.id.toolbar);
        button = findViewById(R.id.save);
        team1 = findViewById(R.id.team1);
        team2 = findViewById(R.id.team2);
        team3 = findViewById(R.id.team3);
        team4 = findViewById(R.id.team4);
        team5 = findViewById(R.id.team5);
        team6 = findViewById(R.id.team6);
        team7 = findViewById(R.id.team7);
        team8 = findViewById(R.id.team8);
        team9 = findViewById(R.id.team9);
        team10 = findViewById(R.id.team10);

        setSupportActionBar(toolbar);

        list = new ArrayList<>();

        Query query = FirebaseDatabase.getInstance().getReference().child("Teams");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Teams model = dataSnapshot.getValue(Teams.class);
                    list.add(model);
                }

                /*if (list != null){
                    String Team1 = list;
                    String Team2 = list.TeamName;
                    String Team3 = list.TeamName;
                    String Team4 = list.TeamName;
                    String Team5 = list.TeamName;
                    String Team6 = list.TeamName;
                    String Team7 = list.TeamName;
                    String Team8 = list.TeamName;
                    String Team9 = list.TeamName;
                    String Team10 = list.TeamName;

                    team1.setText(Team1);
                    team2.setText(Team2);
                    team3.setText(Team3);
                    team4.setText(Team4);
                    team5.setText(Team5);
                    team6.setText(Team6);
                    team7.setText(Team7);
                    team8.setText(Team8);
                    team9.setText(Team9);
                    team10.setText(Team10);
                }*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Something went wrong!", Toast.LENGTH_LONG).show();
            }
        });
    }
}