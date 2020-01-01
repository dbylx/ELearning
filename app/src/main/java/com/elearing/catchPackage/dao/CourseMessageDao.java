package com.elearing.catchPackage.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.elearing.catchPackage.entity.CourseMessage;

import java.util.List;

@Dao
public interface CourseMessageDao {
    @Insert()
    void catchCourseMessage(CourseMessage cm);


    @Query("SELECT * FROM COURSEMESSAGE")
    List<CourseMessage> getAllCourse();


    @Query("SELECT * FROM COURSEMESSAGE WHERE courseName = :inCourseName")
    CourseMessage getCourseByCOurseName(String inCourseName);

    @Delete
    void deleteALl(CourseMessage... cm);

}
