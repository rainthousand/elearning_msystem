package com.system.entity;

//extended class of the Course to add college name
public class CourseCustom extends Course {
    
    private String collegeName;

    public void setcollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getcollegeName() {
        return collegeName;
    }

}
