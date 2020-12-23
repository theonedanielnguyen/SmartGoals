package com.example.smartgoals.daos;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
    private ArrayList<Goal> goalsList = new ArrayList<>();
    private OnRecyclerClickListener listener;

    public GoalAdapter(ArrayList<Goal> goalsList, OnRecyclerClickListener listener) {
        this.goalsList = goalsList;
        this.listener = listener;
//        Log.v("Initialized", "Adapter initialized");
        if (!goalsList.isEmpty()) {
            System.out.println("Print title:");
            System.out.println(goalsList.get(0).getTitle());
        }

    }

    public class GoalViewHolder extends RecyclerView.ViewHolder {
        private TextView nameText;
        private TextView measurement;
        private ProgressBar progress;

        public GoalViewHolder(final View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.goalName);
            measurement = itemView.findViewById(R.id.goalProgress);
            progress = itemView.findViewById(R.id.progressBar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onRecyclerViewItemClicked(getAdapterPosition(), view.getId()
                    );
                }
            });
        }
    }

    @Override
    public GoalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Log.v("CreateViewHolder", "in onCreateViewHolder");
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View view = inflater.inflate(R.layout.goal_items, parent, false);

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.goal_items, parent, false);
        return new GoalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GoalViewHolder holder, int position) {
        Log.v("BindViewHolder", "in onBindViewHolder");

        Goal goal = goalsList.get(position);
        holder.nameText.setText(goal.getTitle());
        //Edit here for actual measurement of goal//
        holder.measurement.setText(goal.getDescription());
        //Edit here for actual measure of progress
        holder.progress.setProgress((int) goal.getUser_goal_id());
        holder.nameText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRecyclerViewItemClicked(position, view.getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return goalsList.size();
    }
}
