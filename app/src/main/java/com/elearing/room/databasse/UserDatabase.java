package com.elearing.room.databasse;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.elearing.room.dao.UserDao;
import com.elearing.room.entity.User;

@Database(entities = {User.class},version = 1,exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
