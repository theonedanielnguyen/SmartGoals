package com.example.smartgoals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

    SharedPreferences sharedPreferences;

    private static final String USER_SESSION = "user_session";
    private static final String USER_ID = "user_id";
    private static final String USER_NAME = "user_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy_dashboard);

        tName = findViewById(R.id.firstName);
//        String firstName = getIntent().getStringExtra("firstName");
//        tName.setText(firstName);
        continueToNewGoal = (FloatingActionButton) findViewById(R.id.addGoal);
        firstGoal = findViewById(R.id.firstGoal);
        secondGoal = findViewById(R.id.secondGoal);
        thirdGoal = findViewById(R.id.thirdGoal);

        sharedPreferences = getSharedPreferences(USER_SESSION, MODE_PRIVATE);

        String name = sharedPreferences.getString(USER_NAME, null);
        Long user_id = sharedPreferences.getLong(USER_ID, 0);

        if (name != null || user_id != null) {
            // set data on test view
            tName.setText("First Name - " +name);
        }


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