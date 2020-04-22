package com.system.entity;

//extended class of Teacher
public class TeacherCustom extends Teacher {
    //the teacher's college name(String)
    private String collegeName;

    public void setcollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getcollegeName() {
        return collegeName;
    }
}
