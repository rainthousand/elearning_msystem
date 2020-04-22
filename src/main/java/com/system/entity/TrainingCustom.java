package com.system.entity;

//extended class of Training
public class TrainingCustom extends Training {
    // add a course name which the training belongs to
    private String CourseName;

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }
}
