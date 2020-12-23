package com.example.smartgoals;


import android.content.Intent;

import android.content.Context;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.smartgoals.daos.GoalDao;
import com.example.smartgoals.databases.GoalDatabase;
import com.example.smartgoals.models.Goal;

public class MainGoalFragment extends Fragment {

    EditText goalName;
    EditText goalCompletionDate;
    EditText goalDescription;
    Button continueToTaskCreation;
    SharedPreferences sharedPreferences;

    private static final String USER_SESSION = "user_session";
    private static final String USER_ID = "user_id";
    private static final String USER_NAME = "user_name";

    public MainGoalFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_goal, container, false);
        goalName = view.findViewById(R.id.mainGoalName);
        goalCompletionDate = view.findViewById(R.id.mainGoalCompletionDate);
        goalDescription = view.findViewById(R.id.mainGoalDescription);
        continueToTaskCreation = view.findViewById(R.id.continueToTasks);


        sharedPreferences = getActivity().getSharedPreferences(USER_SESSION, Context.MODE_PRIVATE);

        Long user_id = sharedPreferences.getLong(USER_ID, 0);

        continueToTaskCreation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Goal newGoal = new Goal();
                newGoal.setTitle(goalName.getText().toString());
                newGoal.setDescription(goalDescription.getText().toString());
                newGoal.setEndDate(goalCompletionDate.getText().toString());
                newGoal.setUser_goal_id(user_id);
                newGoal.setProgress((0));


                GoalDatabase goalDatabase = GoalDatabase.getGoalDatabase(getContext());
                final GoalDao goalDao = goalDatabase.goalDao();
                new Thread(new Runnable () {
                    @Override
                    public void run() {

                        // CREATES NEW GOAL
                        goalDao.insert(newGoal);
                        System.out.println("New goal created!");
//                        Toast.makeText(getContext(), "Goal Created!", Toast.LENGTH_SHORT).show();
                    }
                }).start();

                Intent redirectToDash = new Intent(getContext(), DummyDashboard.class);
                startActivity(redirectToDash);
            }
        });

        return view;
    }
}