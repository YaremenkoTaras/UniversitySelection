package com.epam.autum.selection.database.entity;

/**
 * Created by Tapac on 02.01.2017.
 */
public class Faculty extends Entity {

    private String name;
    private String shortName;
    private Integer numberOfStudent;

    public Faculty() {
    }

    public Faculty(int id, String name, String shortName, Integer numberOfStudent) {
        super(id);
        this.name = name;
        this.shortName = shortName;
        this.numberOfStudent = numberOfStudent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Integer getNumberOfStudent() {
        return numberOfStudent;
    }

    public void setNumberOfStudent(Integer numberOfStudent) {
        this.numberOfStudent = numberOfStudent;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", numberOfStudent=" + numberOfStudent +
                '}';
    }
}
