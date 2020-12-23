package com.example.smartgoals;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ShowGoalActivity extends AppCompatActivity {
    Button home;

    public ShowGoalActivity(){

    }

//        @Override
        protected void onCreate(Bundle savedInstanceState, ViewGroup container, LayoutInflater
        inflater){
        View view = inflater.inflate(R.layout.activity_show_goal, container, false);
        super.onCreate(savedInstanceState);
        home = view.findViewById(R.id.home);
        setContentView(R.layout.activity_show_goal);

        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent redirectToDash = new Intent(getApplicationContext(), DummyDashboard.class);
                startActivity(redirectToDash);
            }
        });
    }

}
