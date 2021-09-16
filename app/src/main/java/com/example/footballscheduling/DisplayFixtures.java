package com.example.footballscheduling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;

public class DisplayFixtures extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_fixtures);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
    }
}