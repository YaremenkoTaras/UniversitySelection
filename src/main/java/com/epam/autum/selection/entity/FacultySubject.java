package com.epam.autum.selection.entity;

/**
 * Created by Tapac on 02.01.2017.
 */
public class FacultySubject {

    private Integer facultyID;
    private Integer subjectID;
    private Integer minMark;

    public FacultySubject() {
    }

    public FacultySubject(Integer facultyID, Integer subjectID, Integer minMark) {
        this.facultyID = facultyID;
        this.subjectID = subjectID;
        this.minMark = minMark;
    }

    public Integer getFacultyID() {
        return facultyID;
    }

    public void setFacultyID(Integer facultyID) {
        this.facultyID = facultyID;
    }

    public Integer getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(Integer subjectID) {
        this.subjectID = subjectID;
    }

    public Integer getMinMark() {
        return minMark;
    }

    public void setMinMark(Integer minMark) {
        this.minMark = minMark;
    }

    @Override
    public String toString() {
        return "FacultySubject{" +
                "facultyID=" + facultyID +
                ", subjectID=" + subjectID +
                ", minMark='" + minMark + '\'' +
                '}';
    }
}
