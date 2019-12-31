package com.elearing.room;

import android.content.Context;

import androidx.room.Room;

import com.elearing.room.databasse.UserDatabase;

public class UserApi {
    private UserApi(){};
    private static UserDatabase userDatabase;

    public static UserDatabase getInstance(Context context){

        if(userDatabase==null){
            userDatabase = Room.databaseBuilder(context, UserDatabase.class, "userLogin.db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
       return userDatabase;
    }
}
