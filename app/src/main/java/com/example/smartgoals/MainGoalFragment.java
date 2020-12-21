package com.example.smartgoals;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class MainGoalFragment extends Fragment {

    EditText goalName;
    EditText goalCompletionDate;
    EditText goalDescription;
    Button continueToTaskCreation;

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

        continueToTaskCreation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container_view, GoalTasksFragment.class, null, "createToTasks");
                transaction.addToBackStack("createToTasks");
                transaction.commit();
            }
        });

        return view;
    }
}