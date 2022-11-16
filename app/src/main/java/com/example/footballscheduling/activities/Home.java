package com.example.footballscheduling.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.footballscheduling.R;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {
    Toolbar toolbar;
    TextView profile, logout, transfers, teamsAndPlayers, fixtures, matchReporting;
    ImageView Profile, Logout, Transfers, TeamsAndPlayers, Fixtures, MatchReporting;
    private long backPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.toolbar);
        profile = findViewById(R.id.tvProfile);
        logout = findViewById(R.id.tvLogoutButton);
        transfers = findViewById(R.id.tvTransfers);
        teamsAndPlayers = findViewById(R.id.tvTeams);
        fixtures = findViewById(R.id.tvFixtures);
        matchReporting = findViewById(R.id.tvMReporting);
        Profile = findViewById(R.id.ivProfile);
        Logout = findViewById(R.id.ivLogoutButton);
        Transfers = findViewById(R.id.ivTransfers);
        TeamsAndPlayers = findViewById(R.id.ivTeams);
        Fixtures = findViewById(R.id.ivFixtures);
        MatchReporting = findViewById(R.id.ivMReporting);

        setSupportActionBar(toolbar);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, com.example.footballscheduling.activities.Profile.class);
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

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
                builder.setMessage("Are you sure you want to log out?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(getApplicationContext(), "LOG OUT SUCCESSFUL", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Home.this, Login.class);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
                builder.setMessage("Are you sure you want to log out?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(getApplicationContext(), "LOG OUT SUCCESSFUL", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Home.this, Login.class);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        transfers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, com.example.footballscheduling.activities.Transfers.class);
                startActivity(intent);
            }
        });

        Transfers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Transfers.class);
                startActivity(intent);
            }
        });

        teamsAndPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, com.example.footballscheduling.activities.TeamsAndPlayers.class);
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
                Intent intent = new Intent(Home.this, DisplayFixtures.class);
                startActivity(intent);
            }
        });

        Fixtures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, DisplayFixtures.class);
                startActivity(intent);
            }
        });

        matchReporting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, com.example.footballscheduling.activities.MatchReporting.class);
                startActivity(intent);
            }
        });

        MatchReporting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, com.example.footballscheduling.activities.MatchReporting.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        long t = System.currentTimeMillis();
        if (t - backPressedTime > 5000){
            backPressedTime = t;
            Toast.makeText(getApplicationContext(), "Press Back again to exit!", Toast.LENGTH_SHORT).show();
        }
        else {
            super.onBackPressed();
        }
    }
}