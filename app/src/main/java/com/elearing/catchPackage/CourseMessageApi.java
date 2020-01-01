package com.elearing.catchPackage;

import android.content.Context;

import androidx.room.Room;

import com.elearing.catchPackage.databasse.CourseMessagedb;

public class CourseMessageApi {
    public static CourseMessagedb courseMessagedb;

    private CourseMessageApi(){};

    public static CourseMessagedb getCourseMessagedb(Context context){
        if(courseMessagedb==null){
            courseMessagedb = Room.databaseBuilder(context, CourseMessagedb.class,"local_db").allowMainThreadQueries().build();
        }
        return courseMessagedb;
    }

}
