package com.epam.autum.selection.jdbc.entity;

import com.epam.autum.selection.jdbc.dto.ApplicantMarkDTO;

public class ApplicantMark extends Entity{

    private Integer mark;
    private User user;
    private Subject  subject;

    public ApplicantMark() {
    }

    public ApplicantMark(Integer id, Integer mark, User user, Subject subject) {
        super(id);
        this.mark = mark;
        this.user = user;
        this.subject = subject;
    }

    public ApplicantMark(ApplicantMarkDTO mark, User user , Subject subject){
        setId(mark.getId());
        setMark(mark.getMark());
        setUser(user);
        setSubject(subject);
    }
    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Subject getSubject() {
        return subject;
    }


    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "ApplicantMark{" +
                "id=" + super.getId() +
                ", mark=" + mark +
                ", user=" + user +
                ", subject='" + subject + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApplicantMark applicantMark = (ApplicantMark) o;

        if (!getMark().equals(applicantMark.getMark())) return false;
        return getSubject().equals(applicantMark.getSubject());

    }

    @Override
    public int hashCode() {
        int result = getMark().hashCode();
        result = 31 * result + getSubject().hashCode();
        return result;
    }
}
