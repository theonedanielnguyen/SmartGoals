package com.example.smartgoals.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.smartgoals.models.Goal;
import com.example.smartgoals.models.User;

import java.util.ArrayList;
import java.util.List;

@Dao
    public interface GoalDao {
        @Insert
        void insert(Goal goal);

        @Update
        void update(Goal goal);

        @Delete
        void delete(Goal goal);

        @Query("DELETE FROM goal_table")
        void deleteAllGoals();

        // NEED THIS FOR SHARED PREFERENCES
        @Query("SELECT * FROM goal_table WHERE goalId LIKE :id")
        Goal getGoalByGoalId(int id);

        //For recycleview
        @Query("SELECT * FROM goal_table WHERE user_goal_id LIKE :id")
        List<Goal> getGoalsByUserId(Long id);
    }