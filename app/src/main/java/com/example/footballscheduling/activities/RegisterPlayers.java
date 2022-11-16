package com.example.footballscheduling.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.footballscheduling.R;
import com.example.footballscheduling.constructors.PlayersRegister;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import org.jetbrains.annotations.NotNull;

public class RegisterPlayers extends AppCompatActivity {
    Toolbar toolbar;
    EditText playerName, idNumber;
    TextView teamParsed;
    Button registerPlayer;
    ProgressDialog progressDialog;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_players);

        toolbar = findViewById(R.id.toolbar);
        playerName = findViewById(R.id.playerNames);
        idNumber = findViewById(R.id.idNumbers);
        teamParsed = findViewById(R.id.teamParsed);
        registerPlayer = findViewById(R.id.registerPlayer);

        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        String key = extras.getString("Key");

        Bundle extras1 = getIntent().getExtras();
        String TeamName = extras1.getString("Team Name");
        teamParsed.setText(TeamName);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Teams").child(key).child("Players");

        registerPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String PlayerName = playerName.getText().toString().trim();
                String IdNumber = idNumber.getText().toString().trim();

                if (PlayerName.isEmpty()) {
                    playerName.setError("Player Name is Required!");
                    playerName.requestFocus();
                    return;
                }

                if (IdNumber.isEmpty()) {
                    idNumber.setError("ID Number is Required!");
                    idNumber.requestFocus();
                    return;
                }

                if (IdNumber.length() != 8) {
                    idNumber.setError("Length for ID Number is 8 characters!");
                    idNumber.requestFocus();
                }

                else {
                    progressDialog = new ProgressDialog(RegisterPlayers.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    addDataToFirebase(PlayerName, IdNumber);
                }
            }
        });
    }

    private void addDataToFirebase(String PlayerName, String IdNumber) {
        Query query = databaseReference.orderByChild("idNumber").equalTo(IdNumber);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Player with that ID Number already exists for this team!", Toast.LENGTH_LONG).show();
                    idNumber.setText("");
                }
                else {
                    PlayersRegister playersRegister = new PlayersRegister(PlayerName, IdNumber);
                    databaseReference.push().setValue(playersRegister, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable @org.jetbrains.annotations.Nullable DatabaseError error, @NonNull @NotNull DatabaseReference ref) {
                            String key = ref.getKey();
                            DatabaseReference reference = databaseReference.child(key);
                            reference.child("key").setValue(key).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        progressDialog.dismiss();
                                        Toast.makeText(getApplicationContext(), "Your player has been registered successfully for your team", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(RegisterPlayers.this, DisplayPlayers.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else {
                                        progressDialog.dismiss();
                                        Toast.makeText(getApplicationContext(), "Your player has not been registered", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}