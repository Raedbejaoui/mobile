package com.example.internship.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.internship.dao.FilesDao;
import com.example.internship.dao.UserDao;
import com.example.internship.dao.IntershipDao;
import com.example.internship.entity.File;
import com.example.internship.entity.Intership;
import com.example.internship.entity.User;

import java.nio.file.Files;


@Database(entities = {
        User.class,
        Intership.class,
        File.class
}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {


    private static AppDataBase instance;


    public abstract UserDao userDao();

    public abstract IntershipDao intershipDao();

    public abstract FilesDao filesDao();

    public static AppDataBase getAppDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "intershipp_db")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

}