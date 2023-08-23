package com.sprinboot.webflux.reactiverest.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "taskschedule")
public class TaskSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Employee employee;

    @Column(name = "taskDate")
    private String taskDate;
    @Column(name = "assignedTask")
    private String assignedTask;
    @Column(name = "taskDetails")
    private String taskDetails;



    public TaskSchedule() {
    }

    public TaskSchedule(int id, Employee employee, String taskDate, String assignedTask, String taskDetails) {
        this.id = id;
        this.employee = employee;
        this.taskDate = taskDate;
        this.assignedTask = assignedTask;
        this.taskDetails = taskDetails;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public void setAssignedTask(String assignedTask) {
        this.assignedTask = assignedTask;
    }

    public void setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    public int getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public String getAssignedTask() {
        return assignedTask;
    }

    public String getTaskDetails() {
        return taskDetails;
    }
    public void setId(int count) {
    }


}
