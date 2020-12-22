package com.example.smartgoals.models;

import androidx.room.Embedded;
import androidx.room.Relation;
import java.util.List;

public class UserWithGoals {

    @Embedded
    public User user;

    @Relation(
            parentColumn = "id",
            entityColumn = "goal_id"
    )

    public List<Goal> goals;

    public UserWithGoals(User user, List<Goal> goals){
        this.user = user;
        this.goals = goals;
    }
}
