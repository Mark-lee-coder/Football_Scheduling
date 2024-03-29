package com.example.footballscheduling.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.footballscheduling.R;
import com.example.footballscheduling.constructors.TeamRegister;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import org.jetbrains.annotations.NotNull;

public class RegisterTeam extends AppCompatActivity {
    Toolbar toolbar;
    EditText teamName;
    Button submit;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_team);

        toolbar = findViewById(R.id.toolbar);
        teamName = findViewById(R.id.team_name);
        submit = findViewById(R.id.submitTeam);

        setSupportActionBar(toolbar);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Teams");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TeamName = teamName.getText().toString().trim();

                if (TeamName.isEmpty()){
                    teamName.setError("Team Name is Required!");
                    teamName.requestFocus();
                }

                else {
                    progressDialog = new ProgressDialog(RegisterTeam.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    addDataToFirebase(TeamName);
                }
            }
        });
    }

    private void addDataToFirebase(String TeamName){
        Query query = databaseReference.orderByChild("TeamName").equalTo(TeamName);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Team Already Exists!", Toast.LENGTH_LONG).show();
                    teamName.setText("");
                }
                else {
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("Teams");
                    ValueEventListener valueEventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                long count = dataSnapshot.child("Teams").getChildrenCount();
                                Log.d("TAG", "Count: " + count);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    };
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.getChildrenCount() >= 8){
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "You cannot register more than 8 teams!", Toast.LENGTH_LONG).show();
                                teamName.setText("");
                            }
                            else {
                                TeamRegister teamRegister = new TeamRegister(TeamName);
                                databaseReference.push().setValue(teamRegister, new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                       String key = ref.getKey();
                                       DatabaseReference reference = databaseReference.child(key);
                                       reference.child("key").setValue(key).addOnCompleteListener(new OnCompleteListener<Void>() {
                                           @Override
                                           public void onComplete(@NonNull Task<Void> task) {
                                              if (task.isSuccessful()){
                                                  progressDialog.dismiss();
                                                  Toast.makeText(getApplicationContext(), "Your team has been registered successfully", Toast.LENGTH_LONG).show();
                                                  Intent intent = new Intent(RegisterTeam.this, TeamsAndPlayers.class);
                                                  startActivity(intent);
                                                  finish();
                                              }
                                           }
                                       });
                                    }
                                });
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

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