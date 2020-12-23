package com.example.smartgoals;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowGoalActivity extends AppCompatActivity {
    Button home;
    ProgressBar pb;
    Button increase;
    Button decrease;
    TextView progress;
    TextView myGoal;
    TextView description;
    TextView percent;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_goal);
        home = findViewById(R.id.home);
        pb = findViewById(R.id.progressBar);
        increase = findViewById(R.id.button_increm);
        decrease = findViewById(R.id.button_decr);
        progress = findViewById(R.id.text_view_progress);
        myGoal = findViewById(R.id.textView6);
        description = findViewById(R.id.textView7);
        percent =findViewById(R.id.text_view_progress_percent);

        myGoal.setText(getIntent().getExtras().getString("title"));
        description.setText(getIntent().getExtras().getString("description"));
        pb.setProgress(getIntent().getExtras().getInt("progress"));
        progress.setText(getIntent().getExtras().getInt("progress")+"");
        percent.setText("%");


        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent redirectToDash = new Intent(getApplicationContext(), DummyDashboard.class);
                startActivity(redirectToDash);
            }
        });

        increase.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                pb.incrementProgressBy(10);
                progress.setText(((Integer.parseInt(progress.getText().toString())) +10) + "");
            }

        });
        decrease.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                pb.incrementProgressBy(-10);
                progress.setText(((Integer.parseInt(progress.getText().toString())) -10) + "");
            }
        });
    }

}
