package com.example.smartgoals.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.smartgoals.models.Goal;
import com.example.smartgoals.models.User;
import com.example.smartgoals.models.UserWithGoals;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("DELETE FROM user_table")
    void deleteAllUsers();

    @Transaction
    @Insert
    long insertUser(User user);

    @Insert
    void insertGoals(List<Goal> goals);

    // NEED THIS FOR SHARED PREFERENCES
    @Query("SELECT * FROM user_table WHERE id LIKE :id")
    User getUserById(int id);

    // NEED THIS TO ENSURE NO REPEATED ACCOUNTS
    @Query("SELECT * FROM user_table WHERE email LIKE :email")
    User getUserByEmail(String email);

    // LOGIN DAO METHOD
    @Query("SELECT * FROM user_table WHERE email=(:email) AND password=(:password)")
    User login(String email, String password);

}
