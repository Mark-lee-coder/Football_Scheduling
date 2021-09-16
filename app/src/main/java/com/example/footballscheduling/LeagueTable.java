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
    TextView team1,team2,team3,team4,team5,team6;
    EditText played1,won1,lost1,drawn1,points1;
    EditText played2,won2,lost2,drawn2,points2;
    EditText played3,won3,lost3,drawn3,points3;
    EditText played4,won4,lost4,drawn4,points4;
    EditText played5,won5,lost5,drawn5,points5;
    EditText played6,won6,lost6,drawn6,points6;

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
        played1 = findViewById(R.id.played1);
        played2 = findViewById(R.id.played2);
        played3 = findViewById(R.id.played3);
        played4 = findViewById(R.id.played4);
        played5 = findViewById(R.id.played5);
        played6 = findViewById(R.id.played6);
        won1 = findViewById(R.id.won1);
        won2 = findViewById(R.id.won2);
        won3 = findViewById(R.id.won3);
        won4 = findViewById(R.id.won4);
        won5 = findViewById(R.id.won5);
        won6 = findViewById(R.id.won6);
        lost1 = findViewById(R.id.lost1);
        lost2 = findViewById(R.id.lost2);
        lost3 = findViewById(R.id.lost3);
        lost4 = findViewById(R.id.lost4);
        lost5 = findViewById(R.id.lost5);
        lost6 = findViewById(R.id.lost6);
        drawn1 = findViewById(R.id.drawn1);
        drawn2 = findViewById(R.id.drawn2);
        drawn3 = findViewById(R.id.drawn3);
        drawn4 = findViewById(R.id.drawn4);
        drawn5 = findViewById(R.id.drawn5);
        drawn6 = findViewById(R.id.drawn6);
        points1 = findViewById(R.id.points1);
        points2 = findViewById(R.id.points2);
        points3 = findViewById(R.id.points3);
        points4 = findViewById(R.id.points4);
        points5 = findViewById(R.id.points5);
        points6 = findViewById(R.id.points6);

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