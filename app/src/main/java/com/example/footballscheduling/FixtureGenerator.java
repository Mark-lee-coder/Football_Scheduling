package com.example.footballscheduling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.DialogInterface;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FixtureGenerator extends AppCompatActivity {
    Toolbar toolbar;
    TextView fixture;
    RecyclerView recyclerView;
    private AdapterFixtureTeam adapterFixtureTeam;
    private ArrayList<Teams> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixture_team);

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
                if (numberOfTeams != 4){
                    AlertDialog.Builder builder = new AlertDialog.Builder(FixtureGenerator.this);
                    builder.setMessage("The required number of teams is 4. Make sure you have exactly 4 teams in order to generate fixtures!");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else {
                    new FixturesGenerator<>();
                }
            }
        });
    }

    public class FixturesGenerator<T extends Object>{
        public List<List<Fixture<T>>> getFixtures(List<T> teams){
            int numberOfTeams = teams.size();
            int totalRounds = numberOfTeams - 1;
            int matchesPerRound = numberOfTeams / 2;
            List<List<Fixture<T>>> rounds = new LinkedList<List<Fixture<T>>>();

            for (int round = 0; round < totalRounds; round++){
                List<Fixture<T>> fixtures = new LinkedList<Fixture<T>>();
                for (int match = 0; match < matchesPerRound; match++){
                    int home = (round + match) % (numberOfTeams - 1);
                    int away = (numberOfTeams - 1 - match + round) % (numberOfTeams - 1);

                    if (match == 0){
                        away = numberOfTeams - 1;
                    }
                    fixtures.add(new Fixture<T>(teams.get(home), teams.get(away)));
                }
                rounds.add(fixtures);
            }
            return rounds;
            //Log.i(Tag, "Fixtures", +rounds);
        }
    }
}