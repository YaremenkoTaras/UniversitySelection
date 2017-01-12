package com.epam.autum.selection.entity;

/**
 * Created by Tapac on 02.01.2017.
 */
public class Subject extends Entity{

    private String name;

    public Subject(){

    }

    public Subject(int id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "ID=" + getId() +
                ", name='" + name + '\'' +
                '}';
    }
}
