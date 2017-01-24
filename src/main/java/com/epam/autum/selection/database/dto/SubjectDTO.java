package com.epam.autum.selection.database.dto;

import com.epam.autum.selection.database.entity.FacultySubject;
import com.epam.autum.selection.database.entity.Subject;

/**
 * Created by Tapac on 24.01.2017.
 */
public class SubjectDTO {
    private Integer facultyID;
    private String subject;
    private Integer minMark;

    public SubjectDTO() {
    }

    public SubjectDTO(Integer facultyID, String subject, Integer minMark) {
        this.facultyID = facultyID;
        this.subject = subject;
        this.minMark = minMark;
    }
    public SubjectDTO(FacultySubject facultySubject, Subject subject) {
        setFacultyID(facultySubject.getFacultyID());
        setSubject(subject.getName());
        setMinMark(facultySubject.getMinMark());
    }

    public Integer getFacultyID() {
        return facultyID;
    }

    public void setFacultyID(Integer facultyID) {
        this.facultyID = facultyID;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getMinMark() {
        return minMark;
    }

    public void setMinMark(Integer minMark) {
        this.minMark = minMark;
    }

    @Override
    public String toString() {
        return "SubjectDTO{" +
                "facultyID=" + facultyID +
                ", subjectID=" + subject +
                ", minMark='" + minMark + '\'' +
                '}';
    }
}
