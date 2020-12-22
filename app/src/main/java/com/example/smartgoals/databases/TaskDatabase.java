package com.example.smartgoals.databases;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.smartgoals.daos.TaskDao;
import com.example.smartgoals.models.Task;

@Database(entities = {Task.class}, version = 1)
public abstract class TaskDatabase extends RoomDatabase {
    public static TaskDatabase taskDatabase;

    public abstract TaskDao taskDao();

    public static synchronized TaskDatabase getTaskDatabase(Context context){
        if(taskDatabase == null){
            taskDatabase = Room.databaseBuilder(context.getApplicationContext(), TaskDatabase.class, "task_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return taskDatabase;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            new TaskDatabase.PopulateDbAsyncTask(taskDatabase).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private TaskDao taskDao;

        private PopulateDbAsyncTask(TaskDatabase db){
            taskDao = db.taskDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskDao.insert(new Task(-1, "Drink water daily", "2021/01/10", "Drink at least 8 glasses of water each day", 8, "glasses"));
            return null;
        }
    }
}
