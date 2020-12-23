package com.example.smartgoals.daos;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.smartgoals.R;
import com.example.smartgoals.models.Goal;

import java.util.ArrayList;
import java.util.List;

public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.GoalViewHolder> {
    private List<Goal> arrayList = new ArrayList<>();

    public GoalAdapter(List<Goal> arrayList) {
        this.arrayList = arrayList;
        Log.v("Initialized", "Adapter initialized");
        if (!arrayList.isEmpty()) {
            System.out.println("Print title:");
            System.out.println(arrayList.get(0).getTitle());
        }

    }

    public class GoalViewHolder extends RecyclerView.ViewHolder {
        public TextView nameText;
        public TextView measurement;
        public ProgressBar progress;

        public GoalViewHolder(@NonNull View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.goalName);
            measurement = itemView.findViewById(R.id.goalProgress);
            progress = itemView.findViewById(R.id.progressBar);

        }
    }

    @Override
    public GoalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.v("CreateViewHolder", "in onCreateViewHolder");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.goal_item, parent, false);
        return new GoalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GoalViewHolder holder, int position) {
        Log.v("BindViewHolder", "in onBindViewHolder");
        Goal goal = arrayList.get(position);
        holder.nameText.setText(goal.getTitle());
        //Edit here for actual measurement of goal//
        holder.measurement.setText(goal.getDescription());
        //Edit here for actual measure of progress
        holder.progress.setProgress((int) goal.getUser_goal_id());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
