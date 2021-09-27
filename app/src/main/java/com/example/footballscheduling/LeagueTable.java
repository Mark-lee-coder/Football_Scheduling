package com.example.footballscheduling;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LeagueTable extends AppCompatActivity {
    Toolbar toolbar;
    TableLayout tableLayout;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_table);

        tableLayout = findViewById(R.id.table123);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        DatabaseReference reference = firebaseDatabase.getReference().child("Teams");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    String name = snapshot1.child("TeamName").getValue().toString();
                    /*String won = snapshot1.child("Won").getValue().toString();
                    String drawn = snapshot1.child("Drawn").getValue().toString();
                    String lost = snapshot1.child("Lost").getValue().toString();
                    String points = snapshot1.child("Points").getValue().toString();*/

                    TableRow tableRow = new TableRow(LeagueTable.this);
                    tableRow.setLayoutParams(new TableLayout.LayoutParams(
                            TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));

                    TextView tvName = new TextView(LeagueTable.this);
                    tvName.setLayoutParams(new TableRow.LayoutParams(
                            TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));
                    tvName.setGravity(Gravity.LEFT);
                    tvName.setTextSize(10);
                    tvName.setText(name);
                    tableRow.addView(tvName);

                    TextView tvPlayed = new TextView(LeagueTable.this);
                    tvPlayed.setLayoutParams(new TableRow.LayoutParams(
                            TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));
                    tvPlayed.setGravity(Gravity.LEFT);
                    tvPlayed.setTextSize(10);
                    tvPlayed.setText("0");
                    tableRow.addView(tvPlayed);

                    TextView tvWon = new TextView(LeagueTable.this);
                    tvWon.setLayoutParams(new TableRow.LayoutParams(
                            TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));
                    tvWon.setGravity(Gravity.LEFT);
                    tvWon.setTextSize(10);
                    tvWon.setText("0");
                    tableRow.addView(tvWon);

                    TextView tvDrawn = new TextView(LeagueTable.this);
                    tvDrawn.setLayoutParams(new TableRow.LayoutParams(
                            TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));
                    tvDrawn.setGravity(Gravity.LEFT);
                    tvDrawn.setTextSize(10);
                    tvDrawn.setText("0");
                    tableRow.addView(tvDrawn);

                    TextView tvLost = new TextView(LeagueTable.this);
                    tvLost.setLayoutParams(new TableRow.LayoutParams(
                            TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));
                    tvLost.setGravity(Gravity.LEFT);
                    tvLost.setTextSize(10);
                    tvLost.setText("0");
                    tableRow.addView(tvLost);

                    TextView tvPoints = new TextView(LeagueTable.this);
                    tvPoints.setLayoutParams(new TableRow.LayoutParams(
                            TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));
                    tvPoints.setGravity(Gravity.LEFT);
                    tvPoints.setTextSize(10);
                    tvPoints.setText("0");
                    tableRow.addView(tvPoints);

                    tableLayout.addView(tableRow);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}