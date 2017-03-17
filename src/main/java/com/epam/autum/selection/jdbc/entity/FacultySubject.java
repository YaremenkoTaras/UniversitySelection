package com.epam.autum.selection.jdbc.entity;

import com.epam.autum.selection.jdbc.dto.FacultySubjectDTO;

public class FacultySubject {
    private Integer faculty;
    private Subject subject;
    private Integer minMark;

    public FacultySubject() {
    }

    public FacultySubject(Integer facultyID, Subject subject, Integer minMark) {
        this.faculty = facultyID;
        this.subject = subject;
        this.minMark = minMark;
    }
    public FacultySubject(FacultySubjectDTO facultySubjectDTO, Subject subject) {
        setFaculty(facultySubjectDTO.getFacultyID());
        setSubject(subject);
        setMinMark(facultySubjectDTO.getMinMark());
    }

    public Integer getFaculty() {
        return faculty;
    }

    public void setFaculty(Integer faculty) {
        this.faculty = faculty;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
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
        return "FacultySubject{" +
                "faculty=" + faculty +
                ", subjectID=" + subject +
                ", minMark='" + minMark + '\'' +
                '}';
    }
}
