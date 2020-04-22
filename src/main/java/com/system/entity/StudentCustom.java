package com.system.entity;

import java.util.List;

//extended class of student
public class StudentCustom extends Student {
    //college name the student belongs to
    private String collegeName;

    //the student course list
    private List<SelectedCourseCustom> selectedCourseList;


    public void setcollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getcollegeName() {
        return collegeName;
    }

    public void setSelectedCourseList(List<SelectedCourseCustom> selectedCourseList) {
        this.selectedCourseList = selectedCourseList;
    }

    public List<SelectedCourseCustom> getSelectedCourseList() {
        return selectedCourseList;
    }

}
