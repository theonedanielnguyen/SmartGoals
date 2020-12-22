package com.example.smartgoals.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.example.smartgoals.daos.UserDao;
import com.example.smartgoals.databases.UserDatabase;
import com.example.smartgoals.models.Goal;
import com.example.smartgoals.models.User;
import com.example.smartgoals.models.UserWithGoals;

public class UserRepository {
    private UserDao userDao;
    private User foundUser;

    public UserRepository(Application application) {
        UserDatabase userDatabase = UserDatabase.getUserDatabase(application);
        userDao = userDatabase.userDao();
    }

    public void insert(User user) {
        new InsertUserAsyncTask(userDao).execute(user);
    }

    public void update(User user) {
        new UpdateUserAsyncTask(userDao).execute(user);
    }

    public void delete(User user) {
        new DeleteUserAsyncTask(userDao).execute(user);
    }

    public void deleteAllUsers() {
        new DeleteAllUsersAsyncTask(userDao).execute();
    }

    public void insertGoals(UserWithGoals userWithGoals) {
        new InsertGoalsAsyncTask(userDao).execute(userWithGoals);
    }

    public User getUserById() {
        return foundUser;
    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private InsertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private UpdateUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.update(users[0]);
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private DeleteUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.delete(users[0]);
            return null;
        }
    }

    private static class DeleteAllUsersAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;

        private DeleteAllUsersAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteAllUsers();
            return null;
        }
    }

    private static class InsertGoalsAsyncTask extends AsyncTask<UserWithGoals, Void, Void> {
        private UserDao userDaoAsync;

        InsertGoalsAsyncTask(UserDao userDao) {
            userDaoAsync = userDao;
        }

        @Override
        protected Void doInBackground(UserWithGoals... userWithGoals) {
            long identifier = userDaoAsync.insertUser(userWithGoals[0].user);

            for (Goal goal : userWithGoals[0].goals) {
                goal.setGoalId(identifier);
            }
            userDaoAsync.insertGoals(userWithGoals[0].goals);
            return null;

        }
    }
}
