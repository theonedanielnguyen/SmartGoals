package com.example.smartgoals.viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.example.smartgoals.models.Goal;
import com.example.smartgoals.repositories.GoalRepository;

public class GoalViewModel extends AndroidViewModel {
    private GoalRepository goalRepo;

    public GoalViewModel(@NonNull Application application){
        super(application);
        goalRepo = new GoalRepository(application);
    }

    public void insert(Goal goal){ goalRepo.insert(goal); }

    public void update(Goal goal){ goalRepo.update(goal); }

    public void delete(Goal goal){ goalRepo.delete(goal); }

    public void deleteAllGoals(){ goalRepo.deleteAllGoals(); }

}
