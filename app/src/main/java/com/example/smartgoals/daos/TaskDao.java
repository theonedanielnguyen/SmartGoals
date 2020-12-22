package com.example.smartgoals.daos;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.smartgoals.models.Task;

@Dao
public interface TaskDao {
    @Insert
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

    @Query("DELETE FROM task")
    void deleteAllTasks();

    @Query("SELECT * FROM task WHERE task_id LIKE :id")
    Task getTaskByTaskId(int id);
}
