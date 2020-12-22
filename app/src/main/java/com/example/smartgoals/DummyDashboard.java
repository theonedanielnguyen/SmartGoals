package com.example.smartgoals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DummyDashboard extends AppCompatActivity {

    TextView tName;
    FloatingActionButton continueToNewGoal;
    TextView firstGoal;
    TextView secondGoal;
    TextView thirdGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy_dashboard);

        tName = findViewById(R.id.firstName);
        String firstName = getIntent().getStringExtra("firstName");
        tName.setText(firstName);
        continueToNewGoal = (FloatingActionButton) findViewById(R.id.addGoal);
        firstGoal = findViewById(R.id.firstGoal);
        secondGoal = findViewById(R.id.secondGoal);
        thirdGoal = findViewById(R.id.thirdGoal);


        continueToNewGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goalsRedirect = new Intent(getApplicationContext(), GoalsActivity.class);
                startActivity(goalsRedirect);
            }
        });

//        firstGoal.setOnClickListener(new View.OnClickListener() {
//
//        });
//
//        secondGoal.setOnClickListener();

    }
}