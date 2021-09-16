package com.example.footballscheduling;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class LeagueTable extends AppCompatActivity {
    Toolbar toolbar;
    Button save;
    TextView team1,team2,team3,team4,team5,team6,team7,team8, team9,team10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_table);

        toolbar = findViewById(R.id.toolbar);
        save = findViewById(R.id.save);
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

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LeagueTable.this, DisplayLeagueTable.class);
                startActivity(intent);
                finish();
            }
        });
    }
}