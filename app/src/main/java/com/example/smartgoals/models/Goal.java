package com.example.smartgoals.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import java.util.Date;
import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "goal_table")
public class Goal {
    @PrimaryKey(autoGenerate = true)
    private int goalId;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "endDate")
    private String endDate;

    @ForeignKey(
            entity = User.class,
            parentColumns = "id",
            childColumns = "user_goal_id",
            onDelete = CASCADE
    )
    private int user_goal_id;

    public Goal(){

    }

    public Goal(int goalId, String title, String description, String endDate) {
        this.goalId = goalId;
        this.title = title;
        this.description = description;
        this.endDate = endDate;
    }

    public int getGoalId() {
        return goalId;
    }

    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getUser_goal_id() {
        return user_goal_id;
    }

    public void setUser_goal_id(int user_goal_id) {
        this.user_goal_id = user_goal_id;
    }
}
