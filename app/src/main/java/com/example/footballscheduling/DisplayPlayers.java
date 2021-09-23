package com.example.footballscheduling;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;

public class DisplayPlayers extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapterPlayers adapterPlayers;
    private ArrayList<ModelPlayers> list;
    List<ModelPlayers> modelPlayersList;
    String keyword = null;
    Toolbar toolbar;
    EditText search;
    TextView teamParsed;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_players);

        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerview);
        search = findViewById(R.id.inputSearch);
        teamParsed = findViewById(R.id.teamParsed);
        floatingActionButton = findViewById(R.id.player);

        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        String TeamName = extras.getString("TeamName");
        teamParsed.setText(TeamName);
        String key = extras.getString("Key");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();

        FirebaseRecyclerOptions<PlayersRegister> options = new FirebaseRecyclerOptions.Builder<PlayersRegister>().setQuery(FirebaseDatabase.getInstance().getReference().child("Teams").child(key).child("Players"), PlayersRegister.class).build();

        Query query = FirebaseDatabase.getInstance().getReference().child("Teams").child(key).child("Players");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    ModelPlayers modelPlayers = dataSnapshot.getValue(ModelPlayers.class);
                    list.add(modelPlayers);
                }
                adapterPlayers = new MyAdapterPlayers(DisplayPlayers.this, list, key);
                recyclerView.setAdapter(adapterPlayers);
                adapterPlayers.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                keyword = charSequence.toString().toLowerCase();
                modelPlayersList.clear();
                adapterPlayers.notifyDataSetChanged();
                searchPlayers();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                keyword = editable.toString().toLowerCase();
                modelPlayersList.clear();
                adapterPlayers.notifyDataSetChanged();
                searchPlayers();
            }
        });

       floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayPlayers.this, RegisterPlayers.class);
                intent.putExtra("Key", key);
                intent.putExtra("Team Name", TeamName);
                startActivity(intent);
            }
       });
    }

    public void searchPlayers(){
        /*FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("Teams").child(Key).child("Players");
        String Search = search.toString().trim();
        Query query = databaseReference.orderByChild("PlayerName").equalTo(Search);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot snap : snapshot.getChildren()){
                        ModelPlayers modelPlayers = snap.getValue(ModelPlayers.class);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
    }
}