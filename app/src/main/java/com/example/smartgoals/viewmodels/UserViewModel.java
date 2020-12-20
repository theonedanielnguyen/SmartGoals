package com.example.smartgoals.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.smartgoals.models.User;
import com.example.smartgoals.repositories.UserRepository;

public class UserViewModel extends AndroidViewModel {
    private UserRepository userRepo;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepo = new UserRepository(application);
    }

    public void insert(User user) {
        userRepo.insert(user);
    }

    public void update(User user) {
        userRepo.update(user);
    }

    public void delete(User user) {
        userRepo.delete(user);
    }

    public void deleteAllUsers() {
        userRepo.deleteAllUsers();
    }
}
