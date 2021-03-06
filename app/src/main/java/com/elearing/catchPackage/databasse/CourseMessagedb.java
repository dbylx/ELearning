package com.elearing.catchPackage.databasse;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.elearing.catchPackage.dao.CourseMessageDao;
import com.elearing.catchPackage.entity.Course;


@Database(entities = {Course.class},version = 1,exportSchema = false)
public abstract class CourseMessagedb extends RoomDatabase {
    public abstract CourseMessageDao getCourseMessageDao();
}
