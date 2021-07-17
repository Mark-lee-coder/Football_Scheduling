package com.example.footballscheduling;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Home extends AppCompatActivity {
    Toolbar toolbar;
    TextView profile, pTransfer, lTransfer, teamsAndPlayers, fixtures, table;
    ImageView Profile, PTransfer, LTransfer, TeamsAndPlayers, Fixtures, Table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        profile = findViewById(R.id.tvProfile);
        pTransfer = findViewById(R.id.tvPTransfers);
        lTransfer = findViewById(R.id.tvLTransfers);
        teamsAndPlayers = findViewById(R.id.tvTeams);
        fixtures = findViewById(R.id.tvFixtures);
        table = findViewById(R.id.tvLTable);
        Profile = findViewById(R.id.ivProfile);
        PTransfer = findViewById(R.id.ivPTransfers);
        LTransfer = findViewById(R.id.ivLTransfers);
        TeamsAndPlayers = findViewById(R.id.ivTeams);
        Fixtures = findViewById(R.id.ivFixtures);
        Table = findViewById(R.id.ivLTable);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Profile.class);
                startActivity(intent);
            }
        });

        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Profile.class);
                startActivity(intent);
            }
        });

        pTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, PermanentTransfer.class);
                startActivity(intent);
            }
        });

        PTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, PermanentTransfer.class);
                startActivity(intent);
            }
        });

        lTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, LoanTransfer.class);
                startActivity(intent);
            }
        });

        LTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, LoanTransfer.class);
                startActivity(intent);
            }
        });

        teamsAndPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, TeamsAndPlayers.class);
                startActivity(intent);
            }
        });

        TeamsAndPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, TeamsAndPlayers.class);
                startActivity(intent);
            }
        });

        fixtures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, FixtureTeam.class);
                startActivity(intent);
            }
        });

        Fixtures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, FixtureTeam.class);
                startActivity(intent);
            }
        });

        table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "You have clicked league table", Toast.LENGTH_SHORT).show();
            }
        });

        Table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "You have clicked league table", Toast.LENGTH_SHORT).show();
            }
        });
    }
}