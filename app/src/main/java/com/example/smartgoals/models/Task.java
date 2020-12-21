package com.example.smartgoals.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "task")
public class Task {
    @PrimaryKey(autoGenerate = true)
    private int task_id;

    @ColumnInfo(name = "task_name")
    private String name;

    @ColumnInfo(name = "completion_date")
    private Date completionDate;

    @ColumnInfo(name = "info")
    private String info;

    @ColumnInfo(name = "unit")
    private int unit;

    @ColumnInfo(name = "measurement")
    private String measurement;

    @ForeignKey(
            entity = Goal.class,
            parentColumns = "goal_id",
            childColumns = "goal_task_id",
            onDelete = CASCADE
    )
    private int goal_task_id;

    public Task(){

    }

    public Task(int task_id, String name, Date completionDate, String info, int unit, String measurement) {
        this.task_id = task_id;
        this.name = name;
        this.completionDate = completionDate;
        this.info = info;
        this.unit = unit;
        this.measurement = measurement;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }
}
