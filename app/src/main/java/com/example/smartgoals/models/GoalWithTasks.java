package com.example.smartgoals.models;

import androidx.room.Embedded;
import androidx.room.Relation;
import java.util.List;

public class GoalWithTasks {
    @Embedded public Goal goal;
    @Relation(
            parentColumn = "goal_id",
            entityColumn = "task_id"
    )
    public List<Task> tasks;

    public GoalWithTasks(Goal goal, List<Task> tasks){
        this.goal = goal;
        this.tasks = tasks;
    }
}
