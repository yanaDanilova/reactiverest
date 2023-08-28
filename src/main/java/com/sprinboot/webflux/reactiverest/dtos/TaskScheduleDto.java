package com.sprinboot.webflux.reactiverest.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TaskScheduleDto {

    private int id;
    @NotNull(message = "Employee id cannot be null")
    private int employee_id;

    @NotNull
    private String taskDate;

    @Size(min = 2, max = 50, message = "Assigned task length must be between 2 and 50 characters")
    private String assignedTask;

    private String taskDetails;

    public TaskScheduleDto(int id, int employee_id, String taskDate, String assignedTask, String taskDetails) {
        this.id = id;
        this.employee_id = employee_id;
        this.taskDate = taskDate;
        this.assignedTask = assignedTask;
        this.taskDetails = taskDetails;
    }

    public int getId() {
        return id;
    }

    public int getEmployee_id() {
        return employee_id;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
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
}
