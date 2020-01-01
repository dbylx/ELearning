package com.elearing.catchPackage.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CourseMessage {
    @PrimaryKey(autoGenerate = true)
    public  int id;
    public String courseName;
    public String coureInfo;
    public String coursePrice;


    public CourseMessage(@NonNull String courseName) {
        this.courseName = courseName;
    }

    public String teacherName;

    @NonNull
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(@NonNull String courseName) {
        this.courseName = courseName;
    }

    public String getCoureInfo() {
        return coureInfo;
    }

    public void setCoureInfo(String coureInfo) {
        this.coureInfo = coureInfo;
    }

    public String getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(String coursePrice) {
        this.coursePrice = coursePrice;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
