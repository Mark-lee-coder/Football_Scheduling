package com.example.footballscheduling;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.jetbrains.annotations.NotNull;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoanTransfer extends AppCompatActivity {
    private static final int REQUEST_CODE_IMAGE = 101;
    EditText playerName, idNumber, loanFrom, loanTo, loanDate;
    TextView loan;
    Spinner spinner;
    String loan_duration;
    Toolbar toolbar;
    ProgressDialog progressDialog;
    Button submit;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_transfer);

        toolbar = findViewById(R.id.toolbar);
        submit = findViewById(R.id.submit1);
        playerName = findViewById(R.id.playerName1);
        idNumber = findViewById(R.id.idNumber1);
        loanFrom = findViewById(R.id.from);
        loanTo = findViewById(R.id.to);
        loanDate = findViewById(R.id.loan_date);
        loan = findViewById(R.id.textViewLoan);
        spinner = findViewById(R.id.loanDuration);

        setSupportActionBar(toolbar);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("LoanTransfers");

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(com.example.footballscheduling.LoanTransfer.this, R.array.loan_duration, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loan_duration = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                loan_duration = "3 months";
            }
        });

        loanDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(LoanTransfer.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        loanDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoanTransfer.this, DisplayLoans.class);
                startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String PlayerName = playerName.getText().toString().trim();
                String IDNumber = idNumber.getText().toString().trim();
                String LoanFrom = loanFrom.getText().toString().trim();
                String LoanTo = loanTo.getText().toString().trim();
                String LoanDuration = spinner.getSelectedItem().toString();
                String LoanDate = loanDate.getText().toString().trim();
                Pattern pattern = Pattern.compile("\\d{1,2}/\\d{1,2}/\\d{4}");
                Matcher matcher = pattern.matcher(LoanDate);

                if (PlayerName.isEmpty()){
                    playerName.setError("Player Name is Required!");
                    playerName.requestFocus();
                    return;
                }

                if (IDNumber.isEmpty()){
                    idNumber.setError("ID Number is Required!");
                    idNumber.requestFocus();
                    return;
                }


                if (IDNumber.length() != 8) {
                    idNumber.setError("Length for ID Number is 8 characters!");
                    idNumber.requestFocus();
                    return;
                }

                if (LoanFrom.isEmpty()){
                    loanFrom.setError("Where was the player loaned from?");
                    loanFrom.requestFocus();
                    return;
                }

                if (LoanTo.isEmpty()){
                    loanTo.setError("Where is the player being loaned to?");
                    loanTo.requestFocus();
                    return;
                }

                if (LoanDate.isEmpty()){
                    loanDate.setError("Please enter the player's transfer date");
                    loanDate.requestFocus();
                    return;
                }

                if (!matcher.matches()){
                    loanDate.setError("Valid format for date is dd/mm/yyyy");
                    loanDate.requestFocus();
                    return;
                }

                if (LoanFrom.equals(LoanTo)){
                    loanTo.setError("Player cannot be loaned to the same team they were loaned from!");
                    loanTo.requestFocus();
                }

                else {
                    progressDialog = new ProgressDialog(LoanTransfer.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    addDataToFirebase(PlayerName, IDNumber, LoanFrom, LoanTo, LoanDuration, LoanDate);
                }
            }
        });
    }

    private void addDataToFirebase(String PlayerName, String IDNumber, String LoanFrom, String LoanTo, String LoanDuration, String LoanDate){
        TransferLoan transferLoan = new TransferLoan(PlayerName, IDNumber, LoanFrom, LoanTo, LoanDuration, LoanDate);
        databaseReference.push().setValue(transferLoan, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable @org.jetbrains.annotations.Nullable DatabaseError error, @NonNull @NotNull DatabaseReference ref) {
                String key = ref.getKey();
                DatabaseReference reference = databaseReference.child(key);
                reference.child("key").setValue(key).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        if (task.isSuccessful()){
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Loan Transfer Recorded Successfully", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LoanTransfer.this, DisplayLoans.class);
                            startActivity(intent);
                            playerName.setText("");
                            idNumber.setText("");
                            loanFrom.setText("");
                            loanTo.setText("");
                            loanDate.setText("");
                        }
                        else {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Loan Transfer Not Recorded", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}