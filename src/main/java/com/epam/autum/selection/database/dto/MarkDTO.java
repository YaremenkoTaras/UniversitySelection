package com.epam.autum.selection.database.dto;

import com.epam.autum.selection.database.entity.ApplicantMark;
import com.epam.autum.selection.database.entity.Subject;

/**
 * Created by Tapac on 24.01.2017.
 */
public class MarkDTO {

    private Integer id;
    private Integer mark;
    private Integer userID;
    private String  subject;

    public MarkDTO() {
    }

    public MarkDTO(Integer id, Integer mark, Integer userID, String subject) {
        this.id = id;
        this.mark = mark;
        this.userID = userID;
        this.subject = subject;
    }

    public MarkDTO(ApplicantMark mark, Subject subject){
        setId(mark.getId());
        setMark(mark.getMark());
        setUserID(mark.getUserID());
        setSubject(subject.getName());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getSubject() {
        return subject;
    }


    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "MarkDTO{" +
                "id=" + id +
                ", mark=" + mark +
                ", userID=" + userID +
                ", subject='" + subject + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MarkDTO markDTO = (MarkDTO) o;

        if (!getMark().equals(markDTO.getMark())) return false;
        return getSubject().equals(markDTO.getSubject());

    }

    @Override
    public int hashCode() {
        int result = getMark().hashCode();
        result = 31 * result + getSubject().hashCode();
        return result;
    }
}
