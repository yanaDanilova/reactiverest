package com.sprinboot.webflux.reactiverest.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String department;

    @OneToMany(mappedBy = "employee")
    private List<TaskSchedule> taskScheduleList;




    public Employee(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;

    }

    public Employee() {

    }
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getDepartment() {
        return department;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setName(String name) {
        this.name = name;
    }
}
