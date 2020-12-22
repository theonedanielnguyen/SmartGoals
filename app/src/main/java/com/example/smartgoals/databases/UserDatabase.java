package com.example.smartgoals.databases;

import android.content.Context;
import android.os.AsyncTask;
import android.service.autofill.UserData;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.smartgoals.daos.UserDao;
import com.example.smartgoals.models.Goal;
import com.example.smartgoals.models.User;

@Database(entities = {User.class, Goal.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    public static UserDatabase userDatabase;

    public abstract UserDao userDao();

    public static synchronized UserDatabase getUserDatabase(Context context) {
        if (userDatabase == null) {
            userDatabase = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, "user_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return userDatabase;
    }

    // Fill up an example user when database is created
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(userDatabase).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;

        private PopulateDbAsyncTask(UserDatabase db) {
            userDao = db.userDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.insert(new User(-1, "TestFirstName", "TestLastName", "hello@android.com", "Password", "Password"));
            return null;
        }

    }

    ;
}


