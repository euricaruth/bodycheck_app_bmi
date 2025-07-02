package com.example.appbodycheck;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {bmirecord.class}, version = 1, exportSchema = false)
public abstract class appdatabase extends RoomDatabase {

    public abstract bmiDao bmiDao();

    private static volatile appdatabase INSTANCE;

    public static appdatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (appdatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    appdatabase.class, "bmi_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}