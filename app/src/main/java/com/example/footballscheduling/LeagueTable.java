package com.example.footballscheduling;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LeagueTable extends AppCompatActivity {
    Toolbar toolbar;
    Button save;
    TextView team1,team2,team3,team4;
    EditText won1,lost1,drawn1,points1;
    EditText won2,lost2,drawn2,points2;
    EditText won3,lost3,drawn3,points3;
    EditText won4,lost4,drawn4,points4;
    ProgressDialog progressDialog;

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
                String Won1 = won1.getText().toString().trim();
                String Won2 = won2.getText().toString().trim();
                String Won3 = won3.getText().toString().trim();
                String Won4 = won4.getText().toString().trim();
                String Drawn1 = drawn1.getText().toString().trim();
                String Drawn2 = drawn2.getText().toString().trim();
                String Drawn3 = drawn3.getText().toString().trim();
                String Drawn4 = drawn4.getText().toString().trim();
                String Lost1 = lost1.getText().toString().trim();
                String Lost2 = lost2.getText().toString().trim();
                String Lost3 = lost3.getText().toString().trim();
                String Lost4 = lost4.getText().toString().trim();
                String Points1 = points1.getText().toString().trim();
                String Points2 = points2.getText().toString().trim();
                String Points3 = points3.getText().toString().trim();
                String Points4 = points4.getText().toString().trim();

                if (Won1.isEmpty()){
                    won1.setError("Please fill out this field");
                    won1.requestFocus();
                    return;
                }

                if (Won2.isEmpty()){
                    won2.setError("Please fill out this field");
                    won2.requestFocus();
                    return;
                }

                if (Won3.isEmpty()){
                    won3.setError("Please fill out this field");
                    won3.requestFocus();
                    return;
                }

                if (Won4.isEmpty()){
                    won4.setError("Please fill out this field");
                    won4.requestFocus();
                    return;
                }

                if (Drawn1.isEmpty()){
                    drawn1.setError("Please fill out this field");
                    drawn1.requestFocus();
                    return;
                }

                if (Drawn2.isEmpty()){
                    drawn2.setError("Please fill out this field");
                    drawn2.requestFocus();
                    return;
                }

                if (Drawn3.isEmpty()){
                    drawn3.setError("Please fill out this field");
                    drawn3.requestFocus();
                    return;
                }

                if (Drawn4.isEmpty()){
                    drawn4.setError("Please fill out this field");
                    drawn4.requestFocus();
                    return;
                }

                if (Lost1.isEmpty()){
                    team1.setError("Please fill out this field");
                    team1.requestFocus();
                    return;
                }

                if (Lost2.isEmpty()){
                    lost2.setError("Please fill out this field");
                    lost2.requestFocus();
                    return;
                }

                if (Lost3.isEmpty()){
                    lost3.setError("Please fill out this field");
                    lost3.requestFocus();
                    return;
                }

                if (Lost4.isEmpty()){
                    lost4.setError("Please fill out this field");
                    lost4.requestFocus();
                    return;
                }

                if (Points1.isEmpty()){
                    points1.setError("Please fill out this field");
                    points1.requestFocus();
                    return;
                }

                if (Points2.isEmpty()){
                    points2.setError("Please fill out this field");
                    points2.requestFocus();
                    return;
                }

                if (Points3.isEmpty()){
                    points3.setError("Please fill out this field");
                    points3.requestFocus();
                    return;
                }

                if (Points4.isEmpty()){
                    points4.setError("Please fill out this field");
                    points4.requestFocus();
                }

                else {
                    progressDialog = new ProgressDialog(LeagueTable.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                }
            }
        });
    }
}