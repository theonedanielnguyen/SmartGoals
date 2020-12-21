package com.example.smartgoals.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
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
    private Date endDate;

    @ForeignKey(
            entity = User.class,
            parentColumns = "id",
            childColumns = "user_goal_id",
            onDelete = CASCADE
    )
    private int user_goal_id;

    public Goal(){

    }

    public Goal(int goalId, String title, String description, Date endDate) {
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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}
