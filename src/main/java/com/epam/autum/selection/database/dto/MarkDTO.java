package com.epam.autum.selection.database.dto;

/**
 * Created by Tapac on 02.01.2017.
 */
public class MarkDTO{

    private Integer id;
    private Integer mark;
    private String userName;
    private String subject;

    public MarkDTO() {
    }

    public MarkDTO(Integer id, Integer mark, String userName, String subject) {
        this.id = id;
        this.mark = mark;
        this.userName = userName;
        this.subject = subject;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "MarkDTO{" +
                "id=" + id +
                ", mark=" + mark +
                ", userName='" + userName + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
