package com.sprinboot.webflux.reactiverest.entities;

public class TaskSchedule {
    private int id;
    private String employeeName;
    private String taskDate;
    private String assignedTask;
    private String taskDetails;

    public TaskSchedule(int id, String employeeName, String taskDate, String assignedTask, String taskDetails) {
        this.id = id;
        this.employeeName = employeeName;
        this.taskDate = taskDate;
        this.assignedTask = assignedTask;
        this.taskDetails = taskDetails;
    }

    public TaskSchedule() {
    }


    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
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

    public String getEmployeeName() {
        return employeeName;
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

    @Override
    public String toString() {
        return "TaskSchedule{" +
                "id=" + id +
                ", employeeName='" + employeeName + '\'' +
                ", taskDate=" + taskDate +
                ", assignedTask='" + assignedTask + '\'' +
                ", taskDetails='" + taskDetails + '\'' +
                '}';
    }

    public void setId(int count) {
    }
}
