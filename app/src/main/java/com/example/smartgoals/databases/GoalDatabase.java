package com.example.smartgoals.databases;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.smartgoals.daos.GoalDao;
import com.example.smartgoals.models.Goal;

import java.text.SimpleDateFormat;

@Database(entities = {Goal.class}, version=1)
public abstract class GoalDatabase extends RoomDatabase {
    public static GoalDatabase goalDatabase;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

    public abstract GoalDao goalDao();

    public static synchronized GoalDatabase getGoalDatabase(Context context) {
        if (goalDatabase == null) {
            goalDatabase = Room.databaseBuilder(context.getApplicationContext(), GoalDatabase.class, "goal_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return goalDatabase;
    }

    // Fill up an example user when database is created
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new GoalDatabase.PopulateDbAsyncTask(goalDatabase).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private GoalDao goalDao;

        private PopulateDbAsyncTask(GoalDatabase db) {
            goalDao = db.goalDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            goalDao.insert(new Goal(-1, "Lose 10 Lbs", "Drop those holiday lbs by Jan 10.", "2021/01/10", 0));
            return null;
        }

    }
}
