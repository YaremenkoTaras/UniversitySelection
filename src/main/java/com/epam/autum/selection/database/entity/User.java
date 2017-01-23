package com.epam.autum.selection.database.entity;

import java.sql.Date;

/**
 * Created by Tapac on 02.01.2017.
 */
public class User extends Entity{

    private String name;
    private String email;
    private String password;
    private String sex;
    private Date birth;
    private String address;
    private String phone;
    private Integer roleID;

    public User(){
    }

    public User(int id, String name, String email, String password, String sex, Date birth, String address,  String phone, Integer roleID) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.birth = birth;
        this.address = address;
        this.phone = phone;
        this.roleID = roleID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + getId() +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", birth=" + birth +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", roleID=" + roleID +
                ", password='" + password + '\'' +
                '}';
    }
}
