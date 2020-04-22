package com.system.entity;

public class SelectedCourseCustom extends Selectedcourse {

    //add a course information
    private CourseCustom courseCustom;

    //add the student who selected the course
    private StudentCustom studentCustom;


    //judge if it's finished
    private Boolean over = false;


    public Boolean getOver() {
        return over;
    }

    public void setOver(Boolean over) {
        this.over = over;
    }

    public StudentCustom getStudentCustom() {
        return studentCustom;
    }

    public void setStudentCustom(StudentCustom studentCustom) {
        this.studentCustom = studentCustom;
    }

    public CourseCustom getCouseCustom() {
        return courseCustom;
    }

    public void setCouseCustom(CourseCustom couseCustom) {
        this.courseCustom = couseCustom;
    }
}
