package com.example.footballscheduling;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FixtureGenerator extends AppCompatActivity {
    Toolbar toolbar;
    TextView fixture;
    RecyclerView recyclerView;
    private AdapterFixtureTeam adapterFixtureTeam;
    private ArrayList<Teams> list;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixture_generator);

        toolbar = findViewById(R.id.toolbar);
        fixture = findViewById(R.id.fixture);
        recyclerView = findViewById(R.id.recyclerview);

        setSupportActionBar(toolbar);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        list = new ArrayList<>();

        Query query = FirebaseDatabase.getInstance().getReference().child("Teams");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Teams model = dataSnapshot.getValue(Teams.class);
                    list.add(model);
                }
                adapterFixtureTeam = new AdapterFixtureTeam(FixtureGenerator.this, list);
                recyclerView.setAdapter(adapterFixtureTeam);
                adapterFixtureTeam.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        fixture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numberOfTeams = list.size();
                if (numberOfTeams != 8){
                    AlertDialog.Builder builder = new AlertDialog.Builder(FixtureGenerator.this);
                    builder.setMessage("The required number of teams is 8. Make sure you have exactly 8 teams in order to generate fixtures!");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else {
                    FixturesGenerator<Teams> fixturesGenerator = new FixturesGenerator<>();
                    List<List<Fixture<Teams>>> rounds = fixturesGenerator.getFixtures(list);

                    DatabaseReference fixturesRef = firebaseDatabase.getReference().child("Fixtures");
                    int roundCounter = 1;
                    for (List<Fixture<Teams>> round : rounds) {
                        int matchCounter = 1;
                        DatabaseReference roundReference = fixturesRef.child("round" + roundCounter);
                        for (Fixture<Teams> fixture1 : round) {
                            Map<String, Object> map1 = new HashMap<>();
                            map1.put("home", fixture1.getHomeTeam().getTeamName());
                            map1.put("away", fixture1.getAwayTeam().getTeamName());
                            roundReference.child("match" + matchCounter).setValue(map1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(getApplicationContext(), "Fixtures generated successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(FixtureGenerator.this, DisplayFixtures.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                            matchCounter += 1;
                        }
                        roundCounter += 1;
                    }
                }
            }
        });
    }

    public class FixturesGenerator<Teams>{
        public List<List<Fixture<Teams>>> getFixtures(ArrayList<Teams> teams){
            int numberOfTeams = teams.size();
            int totalRounds = numberOfTeams - 1;
            int matchesPerRound = numberOfTeams / 2;
            List<List<Fixture<Teams>>> rounds = new LinkedList<List<Fixture<Teams>>>();

            for (int round = 0; round < totalRounds; round++){
                List<Fixture<Teams>> fixtures = new LinkedList<Fixture<Teams>>();
                for (int match = 0; match < matchesPerRound; match++){
                    int home = (round + match) % (numberOfTeams - 1);
                    int away = (numberOfTeams - 1 - match + round) % (numberOfTeams - 1);

                    if (match == 0){
                        away = numberOfTeams - 1;
                    }
                    fixtures.add(new Fixture<Teams>(teams.get(home), teams.get(away)));
                }
                rounds.add(fixtures);
            }
            return rounds;
        }
    }
}