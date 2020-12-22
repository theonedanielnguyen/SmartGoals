package com.example.smartgoals.viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.smartgoals.models.Task;
import com.example.smartgoals.repositories.TaskRepository;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository taskRepo;

    public TaskViewModel(@NonNull Application application){
        super(application);
        taskRepo = new TaskRepository(application);
    }

    public void insert(Task task){ taskRepo.insert(task); }

    public void update(Task task){ taskRepo.update(task); }

    public void delete(Task task){ taskRepo.delete(task); }

    public void deleteAllTasks(){ taskRepo.deleteAllTasks(); }

}
