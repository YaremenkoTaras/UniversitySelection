package com.epam.autum.selection.database.dto;

/**
 * Created by Tapac on 02.01.2017.
 */
public class FacultyDTO{

    private Integer id;
    private String name;
    private String shortName;
    private Integer numberOfStudent;

    public FacultyDTO() {
    }

    public FacultyDTO(int id, String name, String shortName, Integer numberOfStudent) {
        this.id = id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FacultyDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", numberOfStudent=" + numberOfStudent +
                '}';
    }
}
