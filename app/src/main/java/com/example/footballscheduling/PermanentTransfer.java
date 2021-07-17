package com.example.footballscheduling;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.jetbrains.annotations.NotNull;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PermanentTransfer extends AppCompatActivity {
    Toolbar toolbar;
    EditText playerName, idNumber, transferFees, transferFrom, transferTo, transferDate;
    TextView viewPermanent;
    Button submit;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permanent_transfer);

        submit = findViewById(R.id.submit);
        toolbar = findViewById(R.id.toolbar);
        playerName = findViewById(R.id.player_name);
        idNumber = findViewById(R.id.idNumber);
        transferFees = findViewById(R.id.transfer_fees);
        transferFrom = findViewById(R.id.from);
        transferTo = findViewById(R.id.to);
        transferDate = findViewById(R.id.transfer_date);
        viewPermanent = findViewById(R.id.textViewPermanent);

        setSupportActionBar(toolbar);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("PermanentTransfers");

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        transferDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(PermanentTransfer.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        transferDate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String PlayerName = playerName.getText().toString().trim();
                String IDNumber = idNumber.getText().toString().trim();
                String TransferFees = transferFees.getText().toString().trim();
                String TransferFrom = transferFrom.getText().toString().trim();
                String TransferTo = transferTo.getText().toString().trim();
                String TransferDate = transferDate.getText().toString().trim();
                Pattern pattern = Pattern.compile("\\d{1,2}/\\d{1,2}/\\d{4}");
                Matcher matcher = pattern.matcher(TransferDate);

                if (PlayerName.isEmpty()) {
                    playerName.setError("Player Name is Required!");
                    playerName.requestFocus();
                    return;
                }

                if (IDNumber.isEmpty()) {
                    idNumber.setError("ID Number is Required!");
                    idNumber.requestFocus();
                    return;
                }

                if (IDNumber.length() != 8) {
                    idNumber.setError("Length for ID Number is 8 characters!");
                    idNumber.requestFocus();
                    return;
                }

                if (TransferFees.isEmpty()) {
                    transferFees.setError("Please enter the player's transfer fees");
                    transferFees.requestFocus();
                    return;
                }

                if (TransferFrom.isEmpty()) {
                    transferFrom.setError("Where was the player transferred from?");
                    transferFrom.requestFocus();
                    return;
                }

                if (TransferTo.isEmpty()) {
                    transferTo.setError("Where is the player being transferred to?");
                    transferTo.requestFocus();
                    return;
                }

                if (TransferDate.isEmpty()) {
                    transferDate.setError("Please enter the player's transfer date");
                    transferDate.requestFocus();
                    return;
                }

                if (!matcher.matches()){
                    transferDate.setError("Valid format for date is dd/mm/yyyy");
                    transferDate.requestFocus();
                    return;
                }

                 if (TransferFrom.equals(TransferTo)){
                   transferTo.setError("Player cannot be transferred to the same team they were transferred from!");
                   transferTo.requestFocus();
                }

                else {
                    progressDialog = new ProgressDialog(PermanentTransfer.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    addDataToFirebase(PlayerName, IDNumber, TransferFees, TransferFrom, TransferTo, TransferDate);
                }
            }
        });

        viewPermanent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PermanentTransfer.this, DisplayPermanent.class);
                startActivity(intent);
            }
        });
    }

    private void addDataToFirebase(String PlayerName, String IDNumber, String TransferFees, String TransferFrom, String TransferTo, String TransferDate){
        TransferPermanent transferPermanent = new TransferPermanent(PlayerName,  IDNumber, TransferFees, TransferFrom, TransferTo, TransferDate);
        databaseReference.push().setValue(transferPermanent, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable @org.jetbrains.annotations.Nullable DatabaseError error, @NonNull @NotNull DatabaseReference ref) {
                String key = ref.getKey();
                DatabaseReference reference = databaseReference.child(key);
                reference.child("key").setValue(key).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Permanent Transfer Recorded Successfully", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(PermanentTransfer.this, DisplayPermanent.class);
                            startActivity(intent);
                            playerName.setText("");
                            idNumber.setText("");
                            transferFees.setText("");
                            transferFrom.setText("");
                            transferTo.setText("");
                            transferDate.setText("");
                        }
                        else {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Permanent Transfer Not Recorded", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}