package com.aditya.jetpack.helper;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.aditya.jetpack.dao.DaoMovie;
import com.aditya.jetpack.model.ModelFilm;

@Database(entities = {ModelFilm.Result.class},version = 1,exportSchema = false)

@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public static volatile AppDatabase INSTANCE;

    public abstract DaoMovie filmResultDao();

    public static AppDatabase getInstance(Context context){
        if (INSTANCE == null){
            synchronized (AppDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"Movie.db").build();
                }
            }
        }
        return INSTANCE;
    }
}
