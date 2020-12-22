package com.example.smartgoals;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DummyDashboard extends AppCompatActivity {

    TextView tName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy_dashboard);

        tName = findViewById(R.id.firstName);
        String firstName = getIntent().getStringExtra("firstName");
        tName.setText(firstName);

    }
}