package com.epam.autum.selection.jdbc.dto;

import com.epam.autum.selection.jdbc.entity.Entity;

import java.sql.Date;


public class ApplicationDTO extends Entity {

    private Date    date;
    private String  description;
    private Integer overall;
    private Integer facultyID;
    private Integer userID;
    private Integer statusID;

    public ApplicationDTO() {
    }

    public ApplicationDTO(int id, Date date, String description, Integer overall, Integer facultyID, Integer userID, Integer statusID) {
        super(id);
        this.date = date;
        this.description = description;
        this.overall = overall;
        this.facultyID = facultyID;
        this.userID = userID;
        this.statusID = statusID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOverall() {
        return overall;
    }

    public void setOverall(Integer overall) {
        this.overall = overall;
    }

    public Integer getFacultyID() {
        return facultyID;
    }

    public void setFacultyID(Integer facultyID) {
        this.facultyID = facultyID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getStatusID() {
        return statusID;
    }

    public void setStatusID(Integer statusID) {
        this.statusID = statusID;
    }

    @Override
    public String toString() {
        return "ApplicationDTO{" +
                "ID=" + getId() +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", overall=" + overall +
                ", facultyID=" + facultyID +
                ", userID=" + userID +
                ", statusID=" + statusID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApplicationDTO that = (ApplicationDTO) o;

        if (!getOverall().equals(that.getOverall())) return false;
        return getFacultyID().equals(that.getFacultyID());

    }

    @Override
    public int hashCode() {
        int result = getOverall().hashCode();
        result = 31 * result + getFacultyID().hashCode();
        return result;
    }
}
