package com.epam.autum.selection.database.entity;

import com.epam.autum.selection.database.dto.ApplicationDTO;

import java.sql.Date;
import java.time.LocalDate;


public class Application extends Entity{

    private static final String ACCEPT = "ACCEPT";
    private static final String PROCESS = "PROCESS";
    private static final String DECLINE = "DECLINE";

    private LocalDate date;
    private String description;
    private Integer overall;
    private Faculty faculty;
    private User user;
    private String status;

    public Application() {
    }

    public Application(ApplicationDTO app, Faculty faculty, User user) {
        super(app.getId());
        setDate(app.getDate());
        setDescription(app.getDescription());
        setOverall(app.getOverall());
        setStatus(app.getStatusID());
        setFaculty(faculty);
        setUser(user);
    }

    public Application(int id, Date date, String description, Integer overall, Faculty faculty, User user, Integer statusID) {
        super(id);
        this.date = date.toLocalDate();
        this.description = description;
        this.overall = overall;
        this.faculty = faculty;
        this.user = user;
        setStatus(statusID);
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

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public boolean isAccept(){
        return status.equals(ACCEPT);
    }
    public boolean isProcess(){
        return status.equals(PROCESS);
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + super.getId() +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", overall=" + overall +
                ", faculty='" + faculty + '\'' +
                ", user='" + user + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Application that = (Application) o;

        if (!getDate().equals(that.getDate())) return false;
        if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null)
            return false;
        if (!getOverall().equals(that.getOverall())) return false;
        if (!getFaculty().equals(that.getFaculty())) return false;
        if (!getUser().equals(that.getUser())) return false;
        return getStatus().equals(that.getStatus());

    }

    @Override
    public int hashCode() {
        int result = getDate().hashCode();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + getOverall().hashCode();
        result = 31 * result + getFaculty().hashCode();
        result = 31 * result + getUser().hashCode();
        result = 31 * result + getStatus().hashCode();
        return result;
    }
}
