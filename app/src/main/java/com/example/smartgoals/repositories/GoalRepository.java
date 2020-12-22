package com.example.smartgoals.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.example.smartgoals.daos.GoalDao;
import com.example.smartgoals.daos.UserDao;
import com.example.smartgoals.databases.GoalDatabase;
import com.example.smartgoals.models.Goal;
import com.example.smartgoals.models.User;

public class GoalRepository {

    private GoalDao goalDao;
    private Goal foundGoal;

    public GoalRepository(Application application) {
        GoalDatabase goalDatabase = GoalDatabase.getGoalDatabase(application);
        goalDao = goalDatabase.goalDao();
    }

    public void insert(Goal goal) {
        new GoalRepository.InsertGoalAsyncTask(goalDao).execute(goal);
    }

    public void update(Goal goal) {
        new GoalRepository.UpdateGoalAsyncTask(goalDao).execute(goal);
    }

    public void delete(Goal goal) {
        new GoalRepository.DeleteGoalAsyncTask(goalDao).execute(goal);
    }

    public void deleteAllGoals() { new GoalRepository.DeleteAllGoalsAsyncTask(goalDao).execute();
    }

    public Goal getGoalById() { return foundGoal; }

    private static class InsertGoalAsyncTask extends AsyncTask<Goal, Void, Void> {
        private GoalDao goalDao;

        private InsertGoalAsyncTask(GoalDao goalDao) {
            this.goalDao = goalDao;
        }

        @Override
        protected Void doInBackground(Goal... goals) {
            goalDao.insert(goals[0]);
            return null;
        }
    }

    private static class UpdateGoalAsyncTask extends AsyncTask<Goal, Void, Void> {
        private GoalDao goalDao;

        private UpdateGoalAsyncTask(GoalDao goalDao) {
            this.goalDao = goalDao;
        }

        @Override
        protected Void doInBackground(Goal... goals) {
            goalDao.update(goals[0]);
            return null;
        }
    }

    private static class DeleteGoalAsyncTask extends AsyncTask<Goal, Void, Void> {
        private GoalDao goalDao;

        private DeleteGoalAsyncTask(GoalDao goalDao) {
            this.goalDao = goalDao;
        }

        @Override
        protected Void doInBackground(Goal... goals) {
            goalDao.delete(goals[0]);
            return null;
        }
    }

    private static class DeleteAllGoalsAsyncTask extends AsyncTask<Void, Void, Void> {
        private GoalDao goalDao;

        private DeleteAllGoalsAsyncTask(GoalDao goalDao) {
            this.goalDao = goalDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            goalDao.deleteAllGoals();
            return null;
        }
    }
}
