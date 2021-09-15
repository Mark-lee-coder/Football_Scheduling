package com.example.footballscheduling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.widget.Button;

public class LeagueTable extends AppCompatActivity {
    Toolbar toolbar;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_table);

        toolbar = findViewById(R.id.toolbar);
        button = findViewById(R.id.save);

        setSupportActionBar(toolbar);
    }
}