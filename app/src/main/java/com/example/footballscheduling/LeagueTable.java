package com.example.footballscheduling;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class LeagueTable extends AppCompatActivity {
    Toolbar toolbar;
    Button save;
    TextView team1,team2,team3,team4;
    EditText won1,lost1,drawn1,points1;
    EditText won2,lost2,drawn2,points2;
    EditText won3,lost3,drawn3,points3;
    EditText won4,lost4,drawn4,points4;

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
        won1 = findViewById(R.id.won1);
        won2 = findViewById(R.id.won2);
        won3 = findViewById(R.id.won3);
        won4 = findViewById(R.id.won4);
        lost1 = findViewById(R.id.lost1);
        lost2 = findViewById(R.id.lost2);
        lost3 = findViewById(R.id.lost3);
        lost4 = findViewById(R.id.lost4);
        drawn1 = findViewById(R.id.drawn1);
        drawn2 = findViewById(R.id.drawn2);
        drawn3 = findViewById(R.id.drawn3);
        drawn4 = findViewById(R.id.drawn4);
        points1 = findViewById(R.id.points1);
        points2 = findViewById(R.id.points2);
        points3 = findViewById(R.id.points3);
        points4 = findViewById(R.id.points4);

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