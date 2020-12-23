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
import com.example.smartgoals.daos.OnRecyclerClickListener;
import com.example.smartgoals.databases.GoalDatabase;
import com.example.smartgoals.models.Goal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DummyDashboard extends AppCompatActivity {

    TextView tName;
    FloatingActionButton continueToNewGoal;
  
    private RecyclerView recyclerView;
    private ArrayList<Goal> goalArrayList;
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
        goalArrayList = new ArrayList<>();
        setGoalInfo();
//        setAdapter();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView2);

        adapter = new GoalAdapter(goalArrayList, new OnRecyclerClickListener() {
            @Override
            public void onRecyclerViewItemClicked(int position, int id) {
                Intent showGoal = new Intent(getApplicationContext(), ShowGoalActivity.class);
                startActivity(showGoal);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        //End RecyclerView Setup

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
                List<Goal> goalArrayList = new ArrayList<>();
                goalArrayList = goalDao.getGoalsByUserId(user_id);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run(){
                        recyclerView.setAdapter(adapter);
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





    }

//    private void setAdapter() {
//        GoalAdapter goalAdapter = new GoalAdapter(goalArrayList);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(goalAdapter);
//    }

    private void setGoalInfo() {

        Goal myGoal;
        myGoal = new Goal(5,"Java Project", "Finish this app well enough to present to the class at 3PM", "12/23/3030", 75);
        goalArrayList.add(myGoal);

        Goal otherGoal;
        otherGoal = new Goal(6, "Get A Job!", "Find a new job as a really cool junior dev at a really cool company!", "1/1/2021", 14);
        goalArrayList.add(otherGoal);

        Goal thirdGoal;
        thirdGoal = new Goal(7, "Christmas Shopping", "Finish up getting presents for all my friends and family!", "12/24/2020", 97);
        goalArrayList.add(thirdGoal);

        Goal fourthGoal;
        fourthGoal = new Goal(8, "MERN Belt", "Study hard enough to pass MERN!", "1/29/2021", 3);
        goalArrayList.add(fourthGoal);


    }
}