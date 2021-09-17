package com.example.footballscheduling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DisplayLeagueTable extends AppCompatActivity {
    Toolbar toolbar;
    TextView edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_league_table);

        toolbar = findViewById(R.id.toolbar);
        edit = findViewById(R.id.edit);

        setSupportActionBar(toolbar);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DisplayLeagueTable.this, LeagueTable.class);
                startActivity(intent);
                finish();
            }
        });
    }
}