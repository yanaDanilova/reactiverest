package com.sprinboot.webflux.reactiverest.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "department")
    private String department;

    @OneToMany(mappedBy = "employee")
    private List<TaskSchedule> taskScheduleList;

    public Employee(String name, String department) {
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

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setName(String name) {
        this.name = name;
    }
}
