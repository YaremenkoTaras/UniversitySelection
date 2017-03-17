package com.epam.autum.selection.jdbc.dto;

import com.epam.autum.selection.jdbc.entity.Entity;

public class ApplicantMarkDTO extends Entity {

    private Integer mark;
    private Integer userID;
    private Integer subjectID;

    public ApplicantMarkDTO() {
    }

    public ApplicantMarkDTO(int id, Integer mark, Integer userID, Integer subjectID) {

        super(id);
        this.mark = mark;
        this.userID = userID;
        this.subjectID = subjectID;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(Integer subjectID) {
        this.subjectID = subjectID;
    }

    @Override
    public String toString() {
        return "ApplicantMarkDTO{" +
                "ID=" + getId() +
                ", mark=" + mark +
                ", userID=" + userID +
                ", subjectID=" + subjectID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApplicantMarkDTO that = (ApplicantMarkDTO) o;

        if (!mark.equals(that.mark)) return false;
        return subjectID.equals(that.subjectID);

    }

    @Override
    public int hashCode() {
        int result = mark.hashCode();
        result = 31 * result + subjectID.hashCode();
        return result;
    }
}
