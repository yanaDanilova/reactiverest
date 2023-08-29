package com.sprinboot.webflux.reactiverest.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties("id")
public class EmployeeDtoV2 {

    private int id;

    @Size(min = 2, max = 50, message = "Name length must be between 2 and 50 characters")
    private String name;
    @NotNull(message = "Department cannot be null")
    private DepartmentDto department;


    public EmployeeDtoV2(int id, String name, DepartmentDto department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public DepartmentDto getDepartment() {
        return department;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDepartment(DepartmentDto department) {
        this.department = department;
    }
}
