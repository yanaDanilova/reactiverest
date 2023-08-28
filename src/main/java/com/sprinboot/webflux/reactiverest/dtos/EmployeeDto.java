package com.sprinboot.webflux.reactiverest.dtos;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class EmployeeDto {
    private int id;
    @Size(min = 2, max = 50, message = "Name length must be between 2 and 50 characters")
    private String name;


    @NotNull(message = "Department cannot be null")
    private String department;

    public EmployeeDto(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}