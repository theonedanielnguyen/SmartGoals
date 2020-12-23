package com.example.smartgoals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartgoals.daos.GoalAdapter;
import com.example.smartgoals.daos.GoalDao;
import com.example.smartgoals.databases.GoalDatabase;
import com.example.smartgoals.models.Goal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DummyDashboard extends AppCompatActivity {

    TextView tName;
    FloatingActionButton continueToNewGoal;
  
    private RecyclerView recyclerView;
    private List<Goal> goalArrayList = new ArrayList<>();
    private GoalAdapter adapter;

    TextView firstGoal;
    TextView secondGoal;
    TextView thirdGoal;
    Button logout_button;


    SharedPreferences sharedPreferences;

    private static final String USER_SESSION = "user_session";
    private static final String USER_ID = "user_id";
    private static final String USER_NAME = "user_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy_dashboard);

        tName = findViewById(R.id.firstName);
        continueToNewGoal = (FloatingActionButton) findViewById(R.id.addGoal);


        //RecyclerView setup
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView2);

        adapter = new GoalAdapter(goalArrayList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        //End RecyclerView Setup

        firstGoal = findViewById(R.id.firstGoal);
        secondGoal = findViewById(R.id.secondGoal);
        thirdGoal = findViewById(R.id.thirdGoal);
        logout_button = findViewById(R.id.logoutButton);

        sharedPreferences = getSharedPreferences(USER_SESSION, MODE_PRIVATE);

        String name = sharedPreferences.getString(USER_NAME, null);
        Long user_id = sharedPreferences.getLong(USER_ID, 0);

        if (name != null || user_id != 0) {
            // set data on test view
            tName.setText(name);
        }

        //RecyclerView data pull

        new Thread(new Runnable () {
            @Override
            public void run() {
                GoalDatabase goalDatabase = GoalDatabase.getGoalDatabase(getApplicationContext());
                final GoalDao goalDao = goalDatabase.goalDao();

                goalArrayList = goalDao.getGoalsByUserId(user_id);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run(){
                        adapter.notifyDataSetChanged();
                        Log.v("datasetChanged", "notified data change");
                    }
                });
            }
        }).start();

        continueToNewGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goalsRedirect = new Intent(getApplicationContext(), GoalsActivity.class);
                startActivity(goalsRedirect);
            }
        });

        // call button to logout session
         logout_button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 SharedPreferences.Editor editor = sharedPreferences.edit();
                 editor.clear();
                 editor.commit();
                 Toast.makeText(DummyDashboard.this, "Log out successful!", Toast.LENGTH_SHORT).show();
                 finish();
             }
         });



//        firstGoal.setOnClickListener(new View.OnClickListener() {
//
//        });
//
//        secondGoal.setOnClickListener();

    }
}