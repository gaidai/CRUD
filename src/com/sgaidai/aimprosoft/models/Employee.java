package com.sgaidai.aimprosoft.models;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable {

    private static final long serialVersionUID = 6297385302078200511L;

    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private Date birthdate;
    private int department;
    private Date created;
    private Date updated;

    public Employee() {
    }

    public Employee(String firstname, String lastname,  String email, Date birthdate, int department) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
        this.email = email;
        this.birthdate = birthdate;
    }
    public Employee(int id, String firstname, String lastname,  String email, Date birthdate, int department) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
        this.email = email;
        this.birthdate = birthdate;
    }

    public Employee(int id, String firstname, String lastname, String email, Date birthdate, int department, Date created, Date updated) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.birthdate = birthdate;
        this.department = department;
        this.created = created;
        this.updated = updated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", birthdate=" + birthdate +
                ", department=" + department +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
