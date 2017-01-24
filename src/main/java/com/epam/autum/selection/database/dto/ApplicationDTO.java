package com.epam.autum.selection.database.dto;

import com.epam.autum.selection.database.entity.Application;
import com.epam.autum.selection.database.entity.Faculty;
import com.epam.autum.selection.database.entity.User;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by Tapac on 24.01.2017.
 */
public class ApplicationDTO {

    private static final String ACCEPT = "ACCEPT";
    private static final String PROCESS = "PROCESS";
    private static final String DECLINE = "DECLINE";

    private Integer id;

    private LocalDate date;
    private String description;
    private Integer overall;
    private String faculty;
    private String userName;
    private String status;

    public ApplicationDTO() {
    }

    public ApplicationDTO(Application app, Faculty faculty, User user) {
        setId(app.getId());
        setDate(app.getDate());
        setDescription(app.getDescription());
        setOverall(app.getOverall());
        setStatus(app.getStatusID());
        setFaculty(faculty.getName());
        setUserName(user.getName());
    }

    public ApplicationDTO(int id, Date date, String description, Integer overall, String faculty, String userName, Integer statusID) {
        this.id = id;
        this.date = date.toLocalDate();
        this.description = description;
        this.overall = overall;
        this.faculty = faculty;
        this.userName = userName;
        setStatus(statusID);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date.toLocalDate();
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

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        switch (status) {
            case 1:
                this.status = ACCEPT;
                break;
            case 2:
                this.status = PROCESS;
                break;
            case 3:
                this.status = DECLINE;
                break;
            default:
                this.status = DECLINE;
                break;
        }
    }

    @Override
    public String toString() {
        return "ApplicationDTO{" +
                "id=" + id +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", overall=" + overall +
                ", faculty='" + faculty + '\'' +
                ", userName='" + userName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApplicationDTO that = (ApplicationDTO) o;

        if (!getDate().equals(that.getDate())) return false;
        if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null)
            return false;
        if (!getOverall().equals(that.getOverall())) return false;
        if (!getFaculty().equals(that.getFaculty())) return false;
        if (!getUserName().equals(that.getUserName())) return false;
        return getStatus().equals(that.getStatus());

    }

    @Override
    public int hashCode() {
        int result = getDate().hashCode();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + getOverall().hashCode();
        result = 31 * result + getFaculty().hashCode();
        result = 31 * result + getUserName().hashCode();
        result = 31 * result + getStatus().hashCode();
        return result;
    }
}
