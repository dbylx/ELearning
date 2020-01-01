package com.elearing.catchPackage.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.elearing.catchPackage.entity.Course;

import java.util.List;

@Dao
public interface CourseMessageDao {
    @Insert()
    void catchCourseMessage(Course cm);


    @Query("SELECT * FROM course")
    List<Course> getAllCourse();


    @Query("SELECT * FROM course WHERE  name= :inCourseName")
    Course getCourseByCOurseName(String inCourseName);

    @Delete
    void deleteALl(Course... cm);

}
